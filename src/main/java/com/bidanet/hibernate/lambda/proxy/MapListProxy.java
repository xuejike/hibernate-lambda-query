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
public class MapListProxy extends GeterSeterMethodInterceptor {

    protected Map<String,List<Object>> mapList;


    public MapListProxy(Map<String, List<Object>> mapList) {
        this.mapList = mapList;
    }

    public MapListProxy() {
        this.mapList=new HashMap<>();
    }


    public Map<String, List<Object>> getMapList() {
        return mapList;
    }


    @Override
    public void execSeterMethod(Object obj, Method method, String property, Object val) {
        List<Object> list = mapList.get(property);
        if (list==null){
            list= new ArrayList<Object>();
            mapList.put(property,list);
        }

        if (list.indexOf(val)<0){
            list.add(val);
        }
    }
}
