package com.example.demo.session;

/**
 * @author dyanjun
 * @date 2024/1/15 14:33
 * 工厂模式接口，构建SqlSession的工厂
 */
public interface SqlSessionFactory {
    /**
     * 打开一个session
     */
    SqlSession openSession();
}
