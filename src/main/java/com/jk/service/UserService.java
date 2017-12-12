/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:UserService.java 
 * 包名:com.jk.service 
 * 创建日期:2017年11月21日下午1:53:09 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jk.model.User;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：UserService    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月21日 下午1:53:09    
 * 修改人：朱义龙    
 * 修改时间：2017年11月21日 下午1:53:09    
 * 修改备注：       
 * @version </pre>    
 */
public interface UserService {

	/** <pre>selectUserList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月21日 下午1:55:10    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月21日 下午1:55:10    
	 * 修改备注： 
	 * @param user
	 * @return</pre>    
	 */
	List<User> selectUserList(User user)  throws Exception;

	/** <pre>login(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月21日 下午2:08:28    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月21日 下午2:08:28    
	 * 修改备注： 
	 * @param user</pre>    
	 */
	User login(User user, HttpServletRequest request) throws Exception;

	/** <pre>getResourcesList(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月27日 下午4:10:05    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月27日 下午4:10:05    
	 * 修改备注： 
	 * @param login
	 * @return</pre>    
	 */
	User getResourcesList(User login)throws Exception;

}
