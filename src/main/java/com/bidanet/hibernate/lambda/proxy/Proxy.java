package com.bidanet.hibernate.lambda.proxy;


import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xuejike on 2017/3/10.
 */
public class Proxy {
    public static<T>  T proxy(Class<T> tClass, MethodInterceptor methodInterceptor){
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(methodInterceptor);
        enhancer.setSuperclass(tClass);
        return ((T) enhancer.create());
    }

}
