package com.bidanet.hibernate.lambda.proxy;


import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapObjectProxy implements MethodInterceptor {
    protected Map<String,Object> map;

    public MapObjectProxy(Map<String, Object> map) {
        this.map = map;
    }

    public MapObjectProxy() {
        this.map=new HashMap();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
//        System.out.println(PropertyNameTool.getProperty(method.getName()));
        Object o1 = methodProxy.invokeSuper(o, objects);
//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        String methodName = method.getName();
        if (PropertyNameTool.isSeter(methodName)){
            map.put(PropertyNameTool.getProperty(methodName),objects[0]);
        }
        return o1;

    }

    public Map<String, Object> getMap() {
        return map;
    }
}
