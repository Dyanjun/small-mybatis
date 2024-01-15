package com.example.demo.session;

/**
 * @author dyanjun
 * @date 2024/1/15 12:00
 */
public interface SqlSession {
    <T> T selectOne(String statement);
    <T> T selectOne(String statement, Object parameter);
    <T> T getMapper(Class<T> type);
}
