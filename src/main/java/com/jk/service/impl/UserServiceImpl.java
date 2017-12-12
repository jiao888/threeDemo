/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.jk.service.impl 
 * 创建日期:2017年11月21日下午1:53:28 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.UserMapper;
import com.jk.model.Role;
import com.jk.model.User;
import com.jk.service.UserService;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月21日 下午1:53:28    
 * 修改人：朱义龙    
 * 修改时间：2017年11月21日 下午1:53:28    
 * 修改备注：       
 * @version </pre>    
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/* (non-Javadoc)    
	 * @see com.jk.service.UserService#selectUserList(com.jk.model.User)    
	 */
	@Override
	public List<User> selectUserList(User user) throws Exception {
		return userMapper.selectUserList(user);
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.UserService#login(com.jk.model.User)    
	 */
	@Override
	public User login(User user,HttpServletRequest request) throws Exception {
		User userReturn=userMapper.login(user);	
		return userReturn;
	}

	/* (non-Javadoc)    
	 * @see com.jk.service.UserService#getResourcesList(com.jk.model.User)    
	 */
	@Override
	public User getResourcesList(User login) throws Exception {
		
		//定义一个新的set集合接收权限
		Set<String> resources =new HashSet<String>();
		
		//定义一个新的set集合接受角色
		Set<Role> roles =new HashSet<Role>();
		
		//定义一个新的list集合查询当前用户的角色和权限
		List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();
		
		//查询对应角色和权限信息
		list = userMapper.getResourcesList(login);
		
		//把查询的结果放到user对象
		for (Map<String, Object> map : list) {
			if(map.get("url")!=null && !"".equals(map.get("url"))){
				resources.add(map.get("url").toString());
			}
			Role r =new Role();
			r.setId(map.get("roleId").toString());
			r.setName(map.get("name").toString());
			roles.add(r);
		}
		//把查询的所有内容都放到user中
		login.setResources(resources);
		login.setRoles(roles);
		
		return login;
	}
}
