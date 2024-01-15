package com.example.demo.session.defaults;

import com.example.demo.binding.MapperRegistry;
import com.example.demo.session.SqlSession;
import com.example.demo.session.SqlSessionFactory;

/**
 * @author dyanjun
 * @date 2024/1/15 16:28
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
