package com.atguigu.demo.user.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.demo.pojo.entities.DUser;
import com.atguigu.demo.user.service.UserService;

/**
 * @author panpala
 * @date 2017年9月6日
 */
@Path("/user")
public class UserWebService {

	@Autowired
	private UserService userService;
	
	@Path("/regist")
	@POST
	@Consumes("application/json;charset=UTF-8")
	public String regist(DUser user) {
		String result = userService.regist(user);
		return result;
	}
	
	/*
	 * 用户登录，接受另一个服务器发来的请求，
	 * 消费一个json，转换为Duser对象
	 * 生产一个json，转换为Duser对象，返回给方法调用者
	 * */
	@Path("/login") 
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;cahrset=UTF-8")
	public DUser login(DUser user) {
		return userService.login(user);
	}
	
	/*
	 * 用户更新数据
	 * 消费一个json，转换为Duser对象
	 * 生产一个json，转换为Duser对象，返回给方法调用者
	 * */
	@Path("/update") 
	@POST
	@Consumes("application/json;charset=UTF-8")
	@Produces("application/json;cahrset=UTF-8")
	public DUser update(DUser user) {
		return userService.update(user);
	}
	
}
