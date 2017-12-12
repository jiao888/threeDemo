/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:UserController.java 
 * 包名:com.jk.controller 
 * 创建日期:2017年11月21日下午1:46:47 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.model.Json;
import com.jk.model.User;
import com.jk.service.UserService;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：UserController    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月21日 下午1:46:47    
 * 修改人：朱义龙    
 * 修改时间：2017年11月21日 下午1:46:47    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("userController")
public class UserController extends BaseController{

	@Autowired
	private UserService userService; 
	
	@RequestMapping("selectUserList")
	@ResponseBody
	public List<User> selectUserList(User user){
		List<User> list =new ArrayList<User>();
		try {
			list = userService.selectUserList(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("login")
	public void login(User user,HttpServletResponse response,HttpServletRequest request){
		Json json =new Json();
		try {
			User login = userService.login(user,request);

			User u=userService.getResourcesList(login);
			if(login!=null){
				json.setMsg("登录成功");
				json.setSuccess(true);
				request.getSession().setAttribute("user", u);
			}else{
				json.setMsg("失败");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.writeJson(json, response);
	}
	
}
