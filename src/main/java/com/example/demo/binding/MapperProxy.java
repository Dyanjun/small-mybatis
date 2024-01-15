package com.example.demo.binding;

import com.example.demo.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author dyanjun
 * @date 2024/1/14 17:35
 * 映射器代理类
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

  private static final long serialVersionUID = -6424540398559729838L;

  private final Class<T> mapperInterface;
  private SqlSession sqlSession;

  public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
    this.sqlSession = sqlSession;
    this.mapperInterface = mapperInterface;
  }

  /**
   * proxy:代理类代理的真实代理对象com.sun.proxy.$Proxy0
   * method:我们所要调用某个对象真实的方法的Method对象
   * args:指代代理对象方法传递的参数
   */
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (Object.class.equals(method.getDeclaringClass())) { // 如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的
      return method.invoke(this, args);
    } else {
      return sqlSession.selectOne(method.getName(), args); // 代理调用对数据库的操作
    }
  }
}
