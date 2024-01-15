package com.example.demo.session.defaults;

import com.example.demo.binding.MapperRegistry;
import com.example.demo.session.SqlSession;

/**
 * @author dyanjun
 * @date 2024/1/15 14:39
 */
public class DefaultSqlSession implements SqlSession {
    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("proxy: "+statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        Object[] parameters = (Object[]) parameter;
        return (T) ("proxy: method "+ statement + " parameter "+ parameters[0]);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
