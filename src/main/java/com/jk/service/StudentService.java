/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:StudentService.java 
 * 包名:com.jk.service 
 * 创建日期:2017年11月24日上午10:20:23 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service;

import javax.servlet.http.HttpServletResponse;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：StudentService    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月24日 上午10:20:23    
 * 修改人：朱义龙    
 * 修改时间：2017年11月24日 上午10:20:23    
 * 修改备注：       
 * @version </pre>    
 */
public interface StudentService {

	/** <pre>downExcel(这里用一句话描述这个方法的作用)   
	 * 创建人：朱义龙        
	 * 创建时间：2017年11月24日 上午10:22:13    
	 * 修改人：朱义龙       
	 * 修改时间：2017年11月24日 上午10:22:13    
	 * 修改备注： 
	 * @param response</pre>    
	 */
	void downExcel(HttpServletResponse response) throws Exception;

}
