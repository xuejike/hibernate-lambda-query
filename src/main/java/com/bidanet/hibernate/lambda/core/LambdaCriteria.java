package com.bidanet.hibernate.lambda.core;

import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import com.bidanet.hibernate.lambda.proxy.MapListProxy;
import com.bidanet.hibernate.lambda.proxy.MapObjectProxy;
import com.bidanet.hibernate.lambda.proxy.Proxy;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/9.
 */
public class LambdaCriteria<T> implements CriteriaList<T>, CriteriaCount,
        CriteriaWhere<T>, CriteriaFirst<T> {
    protected Session session;
    protected Class<T> tClass;

    // eq  等于
    protected MapObjectProxy eqMapProxy=new MapObjectProxy();
    protected Map<String,Object> eqMap=eqMapProxy.getMap();
    protected T eqBean = Proxy.proxy(tClass,eqMapProxy);;

    // like
    protected MapObjectProxy likeMapProxy=new MapObjectProxy();
    protected Map<String,Object> likeMap=likeMapProxy.getMap();
    protected T likeBean=Proxy.proxy(tClass,likeMapProxy);

    // ne  不等于
    protected MapListProxy neMapListProxy=new MapListProxy();
    protected Map<String, List<Object>> neMap=neMapListProxy.getMapList();
    protected T neBean=Proxy.proxy(tClass,neMapListProxy);

    // in

    // 大于


    // 小于

    // 小于等于


    //大于

    // 大于等于




    protected String orderField="id";
    protected String countField="id";


    public LambdaCriteria(Class<T> tClass, Session session) {
        this.session = session;
        this.tClass=tClass;
    }




    /**
     * 等于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> eq(QueryOne<T> queryOne){

        queryOne.one(eqBean);
        return this;
    }
    public LambdaCriteria<T> eqExample(T example){
        Map<String, Object> map = PropertyNameTool.getMapNotNull(example);
        eqMapProxy.getMap().putAll(map);
        return this;
    }
    /**
     * 构建等于
     * @param criteria
     */
    protected void buildEq(Criteria criteria){
        if (eqMap!=null){
            for (String key : eqMap.keySet()) {
                Object val = eqMap.get(key);
                if (val==null){
                    criteria.add(Restrictions.isNull(key));
                }else{
                    criteria.add(Restrictions.eq(key,val));
                }
            }

        }
    }




    /**
     * like
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> like(QueryOne<T> queryOne){

        queryOne.one(likeBean);
        return this;
    }

    /**
     * 构建Like
     * @param criteria
     */
    protected void  buildLike(Criteria criteria){
        if (likeMap!=null){
            for (String key : likeMap.keySet()) {
                criteria.add(Restrictions.like(key,likeMap.get(key)));
            }
        }
    }



    /**
     * 不等于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> ne(QueryOne<T> queryOne){
        queryOne.one(neBean);
        return this;
    }

    /**
     * 构建不等于 criteria
     * @param criteria
     */
    protected void buildNe(Criteria criteria){
        if (neMap!=null){
            for (String key : neMap.keySet()) {
                List<Object> list = neMap.get(key);
                for (Object val : list) {
                    if (val==null){
                        criteria.add(Restrictions.isNotNull(key));
                    }else{
                        criteria.add(Restrictions.ne(key,val));
                    }
                }

            }
        }
    }



    public Criteria getCriteria(){
        Criteria criteria = session.createCriteria(tClass);
        buildEq(criteria);
        buildLike(criteria);
        buildNe(criteria);
        return criteria;
    }

    @Override
    public List<T> list(){
        return getCriteria().list();
    }

    @Override
    public List<T> list(int pageNo, int pageSize){
        return list(pageNo, pageSize,Order.desc(orderField));
    }
    @Override
    public List<T> list(int pageNo, int pageSize, Order order){
        Criteria criteria = getCriteria();
        createPageOrder(criteria,pageNo,pageSize,order);
        return criteria.list();
    }
    /**
     * 为Criteria 添加 排序以及分页
     * @param criteria 查询Criteria
     * @param pageNo 页号 起始值 1
     * @param pageSize 页大小
     * @param order 排序
     * @return
     */
    protected Criteria createPageOrder(Criteria criteria,int pageNo, int pageSize, Order order){
        return criteria.setFirstResult((pageNo-1) * pageSize)
                .setMaxResults(pageSize)
                .addOrder(order);
    }
    @Override
    public Long count(){

        return count(countField);
    }
    @Override
    public Long count(String property){
        Criteria criteria = getCriteria();
        Long result = (Long) criteria.setProjection(Projections.count(property)).uniqueResult();
        if (result==null){
            result= 0L;
        }
        return result;
    }
    @Override
    public T first(){
        List<T> list = list();
        if (list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }
    @Override
    public void first(QueryOne<T> result){
        T first = first();
        if (first!=null){
            result.one(first);
        }
    }


}
