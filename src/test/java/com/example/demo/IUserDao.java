package com.example.demo;

/**
 * @author dyanjun
 * @date 2024/1/14 19:55
 */
public interface IUserDao {
    String queryUserName(String uId);

    String queryUserAge(String uId);
}
