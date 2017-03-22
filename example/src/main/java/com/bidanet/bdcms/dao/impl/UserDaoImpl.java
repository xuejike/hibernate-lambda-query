package com.bidanet.bdcms.dao.impl;

import com.bidanet.bdcms.core.dao.impl.BaseDaoImpl;
import com.bidanet.bdcms.dao.UserDao;
import com.bidanet.bdcms.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * Created by xuejike on 2017/2/26.
 */
@Repository
public class UserDaoImpl  extends BaseDaoImpl<User> implements UserDao{

    public Session getHbSession(){
        return getSession();
    }

}
