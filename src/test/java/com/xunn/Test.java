package com.xunn;

import com.xunn.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import com.xunn.Employee;

import java.io.InputStream;

public class Test {
    private EmployeeMapper employeeMapper;
    private InputStream is;
    private SqlSession sqlSession;
    @Before
    public void init() throws Exception{
        //目标:获取EmployeeMapper接口的代理对象，并且使用该对象调用selectEmployee(1)方法,然后返回Employee对象
        //1. 将全局配置文件转成字节输入流
        is = Resources.getResourceAsStream("mybatis-config.xml");
        //2. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //3. 使用构建者模式创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //4. 使用工厂模式创建一个SqlSession对象
        sqlSession = sqlSessionFactory.openSession();
        //5. 使用动态代理模式，创建EmployeeMapper接口的代理对象
        employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
    }

    @org.junit.Test
    public void testInsertEmployee(){
        Employee employee = new Employee(null, "Amy", 500d);

        //调用employeeMapper对象的insertEmployee()方法
        employeeMapper.insertEmployee(employee);
    }


    @After
    public void after() throws Exception{
        //提交事务!!!
        sqlSession.commit();
        //7. 关闭资源
        is.close();
        sqlSession.close();
    }

    @org.junit.Test
    public void testDeleteEmployee(){
        employeeMapper.deleteEmployee(2);
    }

    @org.junit.Test
    public void testUpdateEmployee(){
        employeeMapper.updateEmployee(3,"Salar");
    }


    @org.junit.Test
    public void testSelectEmployee(){
        Employee employee=employeeMapper.selectEmployee(1);
        System.out.println(employee);
    }
}
