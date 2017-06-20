package com.bidanet.hibernate.lambda.core;

import com.bidanet.hibernate.lambda.bean.JoinExpression;
import com.bidanet.hibernate.lambda.common.PropertyNameTool;
import com.bidanet.hibernate.lambda.proxy.MapObjectProxy;
import com.bidanet.hibernate.lambda.proxy.Proxy;
import com.bidanet.hibernate.lambda.query.*;
import org.apache.commons.beanutils.ConstructorUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;

import java.util.*;

/**
 * Created by xuejike on 2017/3/9.
 */
public class LambdaCriteria<T> implements ListCriteria<T>, CountCriteria,
        WhereCriteria<T>, FirstCriteria<T>, OrderCriteria<T>, HibernateCriteria {
    protected Session session;
    protected Class<T> tClass;
    protected HashMap<Class,QueryAction<T>>queryActionMap=new HashMap<>(1);
    protected List<WhereCriteria<T>> orCriteriaList=new ArrayList<>(1);

    protected Map<String,JoinExpression> joinExpressionMap=new HashMap<>(1);
    protected HashMap<String, JoinType> joinFieldMap = new HashMap<>(1);
    protected List<Order> orderList=new ArrayList<>(1);


    protected List<Criterion> otherCriterionList =new ArrayList<>();

    protected String orderField="id";
    protected String countField="id";
    private ProjectionList projectionList =Projections.projectionList();


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

    /**
     * 获取 Where查询的 条件集合
     * @return
     */
    @Override
    public List<Criterion> getCriterionList() {
        ArrayList<Criterion> list = new ArrayList<>();
        for (QueryAction<T> action : queryActionMap.values()) {
            List<Criterion> criteria = action.getCriterionList();
            list.addAll(criteria);
        }
        return list;
    }

    /**
     * 获取查询方式
     * @param tc
     * @return
     */
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

    /**
     * 设置 关联查询方式
     * @param propertyName 字段名称
     * @param joinType 关联类型
     */
    public LambdaCriteria<T>  setJoinType(String propertyName,JoinType joinType){
        joinFieldMap.put(propertyName,joinType);
        return this;
    }

    /**
     * 创建Hibernate 查询
     * @return
     */
    public Criteria createBuildCriteria(){
        Criteria criteria = createBuildCriteriaNoOrder();
        //排序
        if (orderList.size()>0){
            for (Order order : orderList) {
                criteria.addOrder(order);
            }
        }




        return criteria;
    }


    public Criteria createBuildCriteriaNoOrder(){
        Criteria criteria = session.createCriteria(tClass);
//        criteria.createAlias()



        for (QueryAction<T> queryAction : queryActionMap.values()) {
            Map<String, JoinType> joinField = queryAction.getJoinField();
            joinFieldMap.putAll(joinField);
        }

        for (Map.Entry<String, JoinExpression> entry : joinExpressionMap.entrySet()) {
            joinFieldMap.put(entry.getKey(),entry.getValue().getJoinType());
        }

        for (Map.Entry<String, JoinType> entry : joinFieldMap.entrySet()) {
            criteria.createAlias(entry.getKey(),PropertyNameTool.JOIN_ALIAS_PREFIX+entry.getKey(),entry.getValue());
        }


        for (QueryAction<T> queryAction : queryActionMap.values()) {
            queryAction.buildCriteria(criteria);
        }


        // Or 查询进行组合
        Criterion[] orCriterion=new Criterion[orCriteriaList.size()];
        for (int i = 0; i < orCriteriaList.size(); i++) {
            WhereCriteria<T> whereCriteria = orCriteriaList.get(i);
            Criterion[] criteria1 = whereCriteria.getCriterionList().toArray(new Criterion[1]);
            orCriterion[i]=Restrictions.conjunction(criteria1);
        }
        criteria.add(Restrictions.or(orCriterion));

        // join 查询进行组合
        for (Map.Entry<String, JoinExpression> entry : joinExpressionMap.entrySet()) {
            String key = entry.getKey();
            JoinExpression value = entry.getValue();
//            criteria = criteria.createAlias(key,"alias_"+key,value.getJoinType());
            List<Criterion> criterionList = value.getWhereCriteria().getCriterionList();
            for (Criterion o : criterionList) {
                criteria.add(o);
            }

        }
        // 其它另外 数据
        for (Criterion criterion : otherCriterionList) {
            criteria.add(criterion);
        }

        if ( projectionList.getLength()>0){
            criteria.setProjection(projectionList);

        }
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
        if (!orderField.equals(order.getPropertyName())){
            criteria.addOrder(order);
        }
        criteria.setFirstResult((pageNo-1) * pageSize)
                .setMaxResults(pageSize);
        return criteria;
    }
    @Override
    public Long count(){

        return count(countField);
    }
    @Override
    public Long count(String property){
        Criteria criteria = createBuildCriteriaNoOrder();
        Long result = (Long) criteria.setProjection(Projections.count(property)).uniqueResult();
        if (result==null){
            result= 0L;
        }
        return result;
    }
    @Override
    public T first(){

        List<T> list = list(1,1);
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

    /**
     * Or 查询 内部是 and
     * @param whereQuery
     * @return
     */
    public LambdaCriteria<T> or(QueryOne<WhereCriteria<T>> whereQuery ){
        LambdaCriteria<T> whereCriteria = new LambdaCriteria<>(tClass, session);
        orCriteriaList.add(whereCriteria);
        whereQuery.one(whereCriteria);
        return this;
    }

    /**
     * 关联查询
     * @param propertyName 关联属性
     * @param propertyClass
     * @param whereQuery
     * @param <P>
     * @return
     */
    public<P> LambdaCriteria<T> join(String propertyName, Class<P> propertyClass
            , QueryOne<WhereCriteria<P>> whereQuery){
        return join(propertyName,propertyClass,whereQuery,JoinType.INNER_JOIN);
    }

    public<P> LambdaCriteria<T> join(String propertyName, Class<P> propertyClass
            , QueryOne<WhereCriteria<P>> whereQuery, JoinType joinType){
        LambdaCriteria<P> whereC = new LambdaCriteria<>(propertyClass, session);
        whereQuery.one(whereC);

        JoinExpression joinExpression = new JoinExpression(propertyName, joinType, whereC);
        joinExpressionMap.put(propertyName,joinExpression);

        return this;
    }
    public<P> LambdaCriteria<T> join(QueryOne<T> joinField
            , QueryOne<WhereCriteria<P>> whereQuery){
        return join(joinField, whereQuery,JoinType.INNER_JOIN);
    }
    public<P> LambdaCriteria<T> join(QueryOne<T> joinField
            , QueryOne<WhereCriteria<P>> whereQuery, JoinType joinType){
        MapObjectProxy proxy = new MapObjectProxy();
        T proxyBean = Proxy.proxy(tClass, proxy);
        joinField.one(proxyBean);

        join(proxy.getLastPropertyName(),proxy.getLastPropertyClass(),whereQuery,joinType);

        return this;
    }

    @Override
    public LambdaCriteria<T> orderDesc(String propertyName){
        Order desc = Order.desc(propertyName);
        orderList.add(desc);
        return this;
    }
    @Override
    public LambdaCriteria<T> orderAsc(String propertyName){
        orderList.add(Order.asc(propertyName));
        return this;
    }

    @Override
    public LambdaCriteria<T> addCriterion(Criterion criterion){
        otherCriterionList.add(criterion);
        return this;
    }

    @Override
    public LambdaCriteria<T> addProjection(Projection projection){
        this.projectionList.add(projection);
        return this;
    }

}
