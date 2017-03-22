package com.bidanet.bdcms.controller;

import com.bidanet.bdcms.VersionRep;
import com.bidanet.bdcms.core.driver.cache.Cache;
import com.bidanet.bdcms.dao.UserDao;
import com.bidanet.bdcms.dao.impl.UserDaoImpl;
import com.bidanet.bdcms.entity.User;
import com.bidanet.bdcms.service.impl.UserServiceImpl;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuejike on 2017/2/26.
 */
@Controller
public class IndexController {

    @Autowired
    UserDaoImpl userDao;

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        List<User> list = userDao.criteriaQuery().eq(query -> query.setName("xjk")).or(w -> {
            w.eq(query -> {

                query.setMobile("12");
            });
        }).or(w -> {
            w.eq(query -> {
                query.setMobile("34");
                query.setUsername("oo");
            });
        }).list();
//        userDao.getHbSession().createCriteria(User.class).add(Restrictions.or(Restrictions.conjunction(
//                Restrictions.eq("name","sss"),
//                Restrictions.eq("id",2L)
//        ),Restrictions.conjunction(
//                Restrictions.eq("name","sss"),
//                Restrictions.eq("id",2L)
//        ))).list();
        return "ss";
    }
}
