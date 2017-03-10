package com.bidanet.hibernate.lambda.proxy;



import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapListProxy implements MethodInterceptor {
    protected Map<String,List<Object>> mapList;

    public MapListProxy(Map<String, List<Object>> mapList) {
        this.mapList = mapList;
    }

    public MapListProxy() {
        this.mapList=new HashMap<>();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        Object o1 = methodProxy.invokeSuper(o, objects);

//        System.out.println("++++++before " + methodProxy.getSuperName() + "++++++");
        String methodName = method.getName();
        if (PropertyNameTool.isSeter(methodName)){
            String property = PropertyNameTool.getProperty(methodName);
            List<Object> list = mapList.get(property);
            if (list==null){
                list= new ArrayList<Object>();
                mapList.put(property,list);
            }
            Object val = objects[0];
            if (list.indexOf(val)<0){
                list.add(val);
            }

        }
        return o1;
    }

    public Map<String, List<Object>> getMapList() {
        return mapList;
    }
}
