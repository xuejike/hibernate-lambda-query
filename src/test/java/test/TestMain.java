package test;

import com.bidanet.hibernate.lambda.core.LambdaCriteria;

/**
 * Created by xuejike on 2017/3/10.
 */
public class TestMain {
    public static void main(String[] arg){
        // eq 查询
        LambdaCriteria<User> lambdaCriteria = new LambdaCriteria<>(User.class, session);
        // select * from user where name = 'name' and sex = '男'
        lambdaCriteria.eq(query ->{
            query.setName("name");
            query.setSex("男");
        }).list();


        lambdaCriteria = new LambdaCriteria<>(User.class, session);
        // select * from user where name = 'name' and ISNULL(sex)
        lambdaCriteria.eq(query ->{
            query.setName("name");
            query.setSex(null);
        }).list();


        lambdaCriteria = new LambdaCriteria<>(User.class, session);
        // like 查询
        //select * from user where name like '%name%'
        lambdaCriteria.like(query ->{
            query.setName("%name%");
        }).list();

        lambdaCriteria = new LambdaCriteria<>(User.class, session);
        // ne 查询
        //select * from user where name <> 'name' and name <> 'pp'
        lambdaCriteria.ne(query -> {
            query.setName("name");
            query.setName("pp");
        }).list();

        lambdaCriteria = new LambdaCriteria<>(User.class, session);
        // 组合使用
        // sselect * from user where open =1 and name like '%xx%' and sex <> '女'
        lambdaCriteria
                .eq(query -> query.setOpen(true))
                .like(query -> query.setName("%xx%"))
                .ne(query -> query.setSex("女")).list();
//        分页  起始值 1
        lambdaCriteria = new LambdaCriteria<>(User.class, session);
        lambdaCriteria
                .eq(query -> query.setOpen(true))
                .like(query -> query.setName("%xx%"))
                .ne(query -> query.setSex("女")).list(1,10);
    }
}
