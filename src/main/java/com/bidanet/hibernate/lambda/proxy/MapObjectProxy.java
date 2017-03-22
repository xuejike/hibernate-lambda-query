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
public class MapObjectProxy extends GeterSeterMethodInterceptor {
    protected Map<String,Object> map;


    public MapObjectProxy(Map<String, Object> map) {
        this.map = map;
    }

    public MapObjectProxy() {
        this.map=new HashMap();
    }

    @Override
    public void execSeterMethod(Object obj, Method method, String property, Object val) {
        map.put(property,val);
    }

    public Map<String, Object> getMap() {
        return map;
    }

}
