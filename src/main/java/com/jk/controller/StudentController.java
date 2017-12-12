/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:StudentController.java 
 * 包名:com.jk.controller 
 * 创建日期:2017年11月24日上午10:19:37 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jk.service.StudentService;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：StudentController    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月24日 上午10:19:37    
 * 修改人：朱义龙    
 * 修改时间：2017年11月24日 上午10:19:37    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("studentController")
public class StudentController {

     @Autowired	
	private  StudentService studentService;
     
     @RequestMapping("downExcel")
     public void downExcel(HttpServletResponse response){
    	 try {
			studentService.downExcel(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}
