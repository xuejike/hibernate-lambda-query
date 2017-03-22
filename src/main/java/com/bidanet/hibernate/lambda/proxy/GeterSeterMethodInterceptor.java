package com.bidanet.hibernate.lambda.proxy;

import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by xuejike on 2017/3/10.
 */
public abstract class GeterSeterMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object val = methodProxy.invokeSuper(o, objects);
        String methodName = method.getName();

        if (PropertyNameTool.isSeter(methodName)){
            // 是 seter 方法
            String property = PropertyNameTool.getProperty(methodName);
            execSeterMethod(o,method,property,objects[0]);
        }else if (PropertyNameTool.isGeter(methodName)){
            // 是geter 方法
            String property = PropertyNameTool.getProperty(methodName);
            execGeterMethod(o,method,property,val);
        }else{
            // 都不是
            notIsGeterSeter(o,method,objects,val);
        }

        return val;
    }

    public  void execGeterMethod(Object obj, Method method, String property, Object val){

    }
    public  void execSeterMethod(Object obj,Method method,String property,Object val){

    }
    public void notIsGeterSeter(Object obj,Method method,Object[] params,Object retVal){

    }
}
