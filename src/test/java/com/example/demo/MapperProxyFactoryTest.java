package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
/**
 * @author dyanjun
 * @date 2024/1/14 19:56
 */
public class MapperProxyFactoryTest {
    @Test
    public void invokeProxyMethod_success(){
        MapperProxyFactory<IUserDao> factory = new MapperProxyFactory<>(IUserDao.class);

        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.example.demo.IUserDao.queryUserName","queryUserName");
        sqlSession.put("com.example.demo.IUserDao.queryUserAge","queryUserAge");
        IUserDao userDao = factory.newInstance(sqlSession);

        String res = userDao.queryUserName("1");
        assertEquals("proxy: queryUserName", res);

        String age = userDao.queryUserAge("1");
        assertEquals("proxy: queryUserAge", age);
    }
}