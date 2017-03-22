# Hibernate Lambda 查询方式[![](https://jitpack.io/v/xuejike/hibernate-lambda-query.svg)](https://jitpack.io/#xuejike/hibernate-lambda-query)
## 支持功能
1. eq 查询
2. like 查询
3. ne 查询

## 使用说明

### 1.引入版本库
```xml
        <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```xml
	<dependency>
    	    <groupId>com.github.xuejike</groupId>
    	    <artifactId>hibernate-lambda-query</artifactId>
    	    <version>0.1.4bate2</version>
    	</dependency>

```
### 2. 基础查询示例
```java
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

```
### 3. or条件查询示例

```java
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
```



### 4. 关联查询示例

```java


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

```


