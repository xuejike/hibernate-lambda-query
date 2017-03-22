package com.bidanet.bdcms.service.impl;

import com.bidanet.bdcms.VersionRep;
import com.bidanet.bdcms.core.dao.Dao;
import com.bidanet.bdcms.core.service.impl.BaseServiceImpl;
import com.bidanet.bdcms.dao.UserDao;
import com.bidanet.bdcms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuejike on 2017/2/26.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> {

    UserDao userDao;
    @Override
    protected Dao getDao() {
        return userDao;
    }
    public void bfTestT(long uid){

//        User user = userDao.get(uid);
//        float v = user.getMoney() - 1;
////        try {
//////            Thread.sleep(100);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        user.setMoney(v);
//        userDao.update(user);
    }
}
