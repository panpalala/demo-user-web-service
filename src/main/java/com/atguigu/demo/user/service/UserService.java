package com.atguigu.demo.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.demo.common.global.GlobalMessage;
import com.atguigu.demo.pojo.entities.DUser;
import com.atguigu.demo.user.mapper.UserMapper;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

/**
 * @author panpala
 * @date 2017年9月6日
 */
@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveUser(DUser user) {
		mapper.insert(new DUser(null, "jane", "123456", null, null, null, null, null, null, null));
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=java.lang.Exception.class)
	public String regist(DUser user) {
		
		//确认数据库是否存在此用户名
		Example example = new Example(DUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userName", user.getUserName());
		int count = mapper.selectCountByExample(example);
		
		if (count > 0) {
			return GlobalMessage.USER_NAME_ALREADY_EXISTS;
		}
		
		//用户可以注册
		mapper.insertSelective(user);
		
		return GlobalMessage.USER_REGIST_SUCCESS;
	}

	
	/**
	 * @param user
	 * @return
	 * 基于注解的声明式事务，开启新事务，异常回滚，只读
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,
					rollbackFor=java.lang.Exception.class,
					readOnly=true)
	public DUser login(DUser user) {
		Example example = new Example(DUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userName", user.getUserName());
		criteria.andEqualTo("userPwd", user.getUserPwd());
		List<DUser> list = mapper.selectByExample(example);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @param user
	 * @return
	 * 更新数据，并查询返回user
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor=java.lang.Exception.class)
	public DUser update(DUser user) {
		mapper.updateByPrimaryKeySelective(user);
		DUser dUser = mapper.selectByPrimaryKey(user.getUserId());
		return dUser;
	}
	
}
