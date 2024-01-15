package com.example.demo.binding;

import com.example.demo.session.SqlSession;
import cn.hutool.core.lang.ClassScanner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dyanjun
 * @date 2024/1/15 11:57
 * 在getmapper的时候再往代理类的传入具体的sqlsession，即在使用的时候才创建真正的代理类，有一丝延迟的感觉
 */
public class MapperRegistry {
  private final Map<Class<?>, MapperProxyFactory<?>> mappers = new HashMap<>();

    /**
     * 根据dao获取代理工厂，根据代理工厂和sqlsession，创建代理类
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
      final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) mappers.get(type);
      if (mapperProxyFactory == null) {
          throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
      }
      try {
          return mapperProxyFactory.newInstance(sqlSession);
      } catch (Exception e) {
          throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
      }
  }

    /**
     * 针对dao生成工厂
     * @param type
     * @param <T>
     */
  public <T> void addMapper(Class<T> type){
      if(type.isInterface()){
          if(hasMapper(type)){
              throw new RuntimeException("Type "+type+" has already existed");
          }
          mappers.put(type, new MapperProxyFactory<>(type));
      }
  }

  public  <T> void addMappers(String packageName){
      Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
      for(Class<?> mapper:mapperSet){
          addMapper(mapper);
      }
  }

  private <T> boolean hasMapper(Class<T> type){
      return mappers.containsKey(type);
  }
}
