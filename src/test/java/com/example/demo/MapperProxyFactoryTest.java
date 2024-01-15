package com.example.demo;

import static org.junit.Assert.assertEquals;

import com.example.demo.binding.MapperProxyFactory;
import com.example.demo.binding.MapperRegistry;
import com.example.demo.dao.ISchoolDao;
import com.example.demo.dao.IUserDao;
import com.example.demo.session.SqlSession;
import com.example.demo.session.SqlSessionFactory;
import com.example.demo.session.defaults.DefaultSqlSessionFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
/**
 * @author dyanjun
 * @date 2024/1/14 19:56
 */
public class MapperProxyFactoryTest {
    @Test
    public void invokeProxyMethod_success(){
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("com.example.demo.dao");

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        ISchoolDao iSchoolDao = sqlSession.getMapper(ISchoolDao.class);




        String res = iUserDao.queryUserName("1");
        assertEquals("proxy: method queryUserName" + " parameter 1", res);

        String age = iSchoolDao.querySchoolName("2");
        assertEquals("proxy: method querySchoolName" + " parameter 2", age);
    }
}