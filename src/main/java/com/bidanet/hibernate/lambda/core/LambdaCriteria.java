package com.bidanet.hibernate.lambda.core;

import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import com.bidanet.hibernate.lambda.query.*;
import org.apache.commons.beanutils.ConstructorUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuejike on 2017/3/9.
 */
public class LambdaCriteria<T> implements CriteriaList<T>, CriteriaCount,
        CriteriaWhere<T>, CriteriaFirst<T> {
    protected Session session;
    protected Class<T> tClass;
    protected HashMap<Class,QueryAction<T>>queryActionMap=new HashMap<>();
    protected List<CriteriaWhere<T>> orCriteriaList=new ArrayList<>(1);




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
        T proxyBean = getQueryAction(EqQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }
    @Override
    public LambdaCriteria<T> eqExample(T example){
        Map<String, Object> map = PropertyNameTool.getMapNotNull(example);
        EqQueryObjectAction eqQueryObjectAction = (EqQueryObjectAction) getQueryAction(EqQueryObjectAction.class);
        eqQueryObjectAction.getMap().putAll(map);
        return this;
    }





    /**
     * like
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> like(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(LikeQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    /**
     * 不等于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> ne(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(NeqQueryListAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    /**
     * 大于等于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> gte(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(GteQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    /**
     *大于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> gt(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(GtQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }
    /**
     * 小于等于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> lte(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(LteQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    /**
     * 小于
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> lt(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(LtQueryObjectAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    /**
     * in 操作
     * @param queryOne
     * @return
     */
    @Override
    public LambdaCriteria<T> in(QueryOne<T> queryOne){
        T proxyBean = getQueryAction(InQueryListAction.class).getProxyBean();
        queryOne.one(proxyBean);
        return this;
    }

    @Override
    public List<Criterion> getCriterionList() {
        ArrayList<Criterion> list = new ArrayList<>();
        for (QueryAction<T> action : queryActionMap.values()) {
            List<Criterion> criteria = action.getCriterionList();
            list.addAll(criteria);
        }
        return list;
    }


    protected QueryAction<T> getQueryAction(Class<? extends QueryAction> tc){
        QueryAction<T> queryAction = queryActionMap.get(tc);
        if (queryAction==null){
            try {
                queryAction = ConstructorUtils.invokeConstructor(tc, tClass);
                queryActionMap.put(tc,queryAction);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queryAction;
    }




    public Criteria createBuildCriteria(){
        Criteria criteria = session.createCriteria(tClass);
        for (QueryAction<T> queryAction : queryActionMap.values()) {
            queryAction.buildCriteria(criteria);
        }

        Criterion[] orCriterion=new Criterion[orCriteriaList.size()];
//        for (CriteriaWhere<T> criteriaWhere : orCriteriaList) {
//            List<Criterion> criterionList = criteriaWhere.getCriterionList();
//            Criterion[] criteria1=new Criterion[criterionList.size()];
//        }
        for (int i = 0; i < orCriteriaList.size(); i++) {
            CriteriaWhere<T> criteriaWhere = orCriteriaList.get(i);
            Criterion[] criteria1 = criteriaWhere.getCriterionList().toArray(new Criterion[1]);
            orCriterion[i]=Restrictions.conjunction(criteria1);
        }
        criteria.add(Restrictions.or(orCriterion));

        return criteria;
    }

    @Override
    public List<T> list(){
        return createBuildCriteria().list();
    }

    @Override
    public List<T> list(int pageNo, int pageSize){
        return list(pageNo, pageSize,Order.desc(orderField));
    }
    @Override
    public List<T> list(int pageNo, int pageSize, Order order){
        Criteria criteria = createBuildCriteria();
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
        Criteria criteria = createBuildCriteria();
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

    public LambdaCriteria<T> or(QueryOne<CriteriaWhere<T>> whereQuery ){
        LambdaCriteria<T> whereCriteria = new LambdaCriteria<>(tClass, session);
        orCriteriaList.add(whereCriteria);
        whereQuery.one(whereCriteria);
        return this;
    }


}
