package com.atguigu.demo.user.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.demo.pojo.entities.DUser;
import com.atguigu.demo.user.mapper.UserMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author panpala
 * @date 2017年9月6日
 */
public class DemoTest {
	
	private ApplicationContext ioC = new ClassPathXmlApplicationContext("spring-context.xml");
	
	@Test
	public void test1() throws SQLException {
		DataSource dataSource = ioC.getBean(ComboPooledDataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
	@Test
	public void test2() {
		UserMapper mapper = ioC.getBean(UserMapper.class);
		mapper.insert(new DUser(null, "panpala", "123456", null, null, null, null, null, null, null));
	}
	
}
