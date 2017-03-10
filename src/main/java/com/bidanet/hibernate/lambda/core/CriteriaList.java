package com.bidanet.hibernate.lambda.core;

import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by xuejike on 2017/3/10.
 */
public interface CriteriaList<T> {
    List<T> list();

    List<T> list(int pageNo, int pageSize);

    List<T> list(int pageNo, int pageSize, Order order);
}
