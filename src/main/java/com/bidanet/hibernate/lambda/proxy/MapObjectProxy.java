package com.bidanet.hibernate.lambda.proxy;


import com.bidanet.hibernate.lambda.annotation.LambdaJoinQuery;
import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.hibernate.sql.JoinType;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/10.
 */
public class MapObjectProxy extends GeterSeterMethodInterceptor {
    protected String prefix="";
    protected Map<String,Object> map;
    protected Map<String,MapObjectProxy> fieldObjectProxyMap=new HashMap<>(1);
    protected Map<String,JoinType> joinFieldMap=new HashMap<>(1);


    public MapObjectProxy(Map<String, Object> map) {
        this.map = map;
    }

    public MapObjectProxy(String prefix, Map<String, Object> map) {
        this.prefix = prefix;
        this.map = map;
    }

    public MapObjectProxy() {
        this.map=new HashMap();
    }

    @Override
    public void execSeterMethod(Object obj, Method method, String property, Object val) {
        map.put(prefix+property,val);
    }

    @Override
    public Object execGeterMethod(Object obj, Method method, String property, Object val) {
//        super.execGeterMethod(obj, method, property, val);
        if (method.getReturnType()!=List.class){
            Entity entity = method.getReturnType().getDeclaredAnnotation(Entity.class);

            if (entity!=null){
                JoinType joinType =JoinType.INNER_JOIN;
                joinFieldMap.put(property,joinType);

                MapObjectProxy mapObjectProxy = fieldObjectProxyMap.get(property);
                if (mapObjectProxy==null){
                    mapObjectProxy=new MapObjectProxy(PropertyNameTool.JOIN_ALIAS_PREFIX+property+".",map);
                }
                return Proxy.proxy(method.getReturnType(),mapObjectProxy);
            }
        }

        return val;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public Map<String, JoinType> getJoinFieldMap() {
        return joinFieldMap;
    }

    public Map<String, MapObjectProxy> getFieldObjectProxyMap() {
        return fieldObjectProxyMap;
    }
}
