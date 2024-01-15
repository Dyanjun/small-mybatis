package com.example.demo.binding;

import com.example.demo.session.SqlSession;
import java.lang.reflect.Proxy;

/**
 * @author dyanjun
 * @date 2024/1/14 17:46
 */
public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }


    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        /**
         * Proxy.newProxyInstance
         * loader：一个classloader对象，定义了由哪个classloader对象对生成的代理类进行加载
         * interfaces：一个interface对象数组，表示我们将要给我们的代理对象提供一组什么样的接口，如果我们提供了这样一个接口对象数组，那么也就是声明了代理类实现了这些接口，代理类就可以调用接口中声明的所有方法。
         * h：一个InvocationHandler对象，表示的是当动态代理对象调用方法的时候会关联到哪一个InvocationHandler对象上，并最终由其调用。
        **/
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
