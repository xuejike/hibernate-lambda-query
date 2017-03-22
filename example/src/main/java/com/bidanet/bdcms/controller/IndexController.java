package com.bidanet.bdcms.controller;

import com.bidanet.bdcms.VersionRep;
import com.bidanet.bdcms.core.driver.cache.Cache;
import com.bidanet.bdcms.dao.UserDao;
import com.bidanet.bdcms.dao.impl.UserDaoImpl;
import com.bidanet.bdcms.entity.Company;
import com.bidanet.bdcms.entity.User;
import com.bidanet.bdcms.service.impl.UserServiceImpl;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
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
    public void index(){
        long begin=System.currentTimeMillis();
        // or 查询
        userDao.criteriaQuery().eq(query -> query.setName("xjk")).or(w -> {
            w.eq(query -> {

                query.setMobile("12");
            });
        }).or(w -> {
            w.eq(query -> {
                query.setMobile("34");
                query.setUsername("oo");
            });
        }).list();

//        List userList = userDao.getHbSession().createCriteria(User.class)
//                .createCriteria("company", JoinType.NONE)
//                .add(Restrictions.eq("id", 1L)).list();

        User user = userDao.get(1L);
//
        List<User> list = userDao.criteriaQuery().<Company>join(j -> {
            j.getCompany();
        }, w -> {
            w.eq(query -> query.setId(1L));
        }, JoinType.LEFT_OUTER_JOIN).list();


        userDao.criteriaQuery().eq(query -> {
            query.setName("xjk");
            query.getCompany().setId(1L);
        }).list();
        System.out.println("-->"+(System.currentTimeMillis()-begin));
    }

    public void orQuery(){
        // SQL  ->  where username='xuejike' and (name='xjk' and nickname='xuejike') or (name='xuejike')
        userDao.criteriaQuery()
                .eq(query -> query.setUsername("xuejike"))
                .or(where->{
                    where.eq(query ->{
                        query.setName("xjk");
                        query.setNickname("xuejike");
                    });
                })
                .or(where->{
                    where.eq(query ->{
                        query.setName("xuejike");}
                );
        }).list();
    }
    @RequestMapping("/joinQuery")
    @ResponseBody
    public String joinQuery(){

        //Join 默认连接方式为 Inner Join

        // SQL   from user inner join company on company.id=user.company_id where username='xuejike' and company.id=1
        userDao.criteriaQuery().eq(query -> {
            query.setUsername("xuejike");
            query.getCompany().setId(1L);
        }).list();

        // SQL   from user inner join company on company.id=user.company_id where username = 'xuejike' and company.id=1
        userDao.criteriaQuery().eq(query -> {
            query.setUsername("xuejike");
            query.getCompany().setId(1L);
        }).list();

        // SQL   from user left outer  join company on company.id=user.company_id where username = 'xuejike' and company.id=1
        userDao.criteriaQuery().eq(query -> {
            query.setUsername("xuejike");
            query.getCompany().setId(1L);
        }).setJoinType("company",JoinType.LEFT_OUTER_JOIN).list();

        // SQL   from user inner join company on company.id=user.company_id where username='xuejike' and company.id=1
        userDao.criteriaQuery().eq(query -> {
            query.setUsername("xuejike");
        }).join("company",Company.class,where->{
            where.eq(query -> {
                query.setId(1L);
            });
        },JoinType.INNER_JOIN).list();
return "s";
    }


}
