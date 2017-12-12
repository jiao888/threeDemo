/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:StudentServiceImpl.java 
 * 包名:com.jk.service.impl 
 * 创建日期:2017年11月24日上午10:20:34 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.StudentMapper;
import com.jk.service.StudentService;
import com.jk.until.ExportExcelMax;
import com.jk.until.PagerEntity;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：StudentServiceImpl    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月24日 上午10:20:34    
 * 修改人：朱义龙    
 * 修改时间：2017年11月24日 上午10:20:34    
 * 修改备注：       
 * @version </pre>    
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;

	/* (non-Javadoc)    
	 * @see com.jk.service.StudentService#downExcel(javax.servlet.http.HttpServletResponse)    
	 */
	@Override
	public void downExcel(final HttpServletResponse response) throws Exception {
		
		//1.查询数据库总条数
		Long count=studentMapper.findCountStudent();
		
		//分页展示每页定义1000条
		Long totalpage =count%1000==0?count/1000:count/1000+1;
		
		//定义workbook对象
		final HSSFWorkbook workbook =new HSSFWorkbook();
		
		//2创建单例线程池
		 ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();  
		  for (int i = 1; i < totalpage; i++) {  
		   final int  a= i;  
		   //3.
		   singleThreadExecutor.execute(new Runnable() {  
			   //4
		    public void run() {  
		          
		    	//查询数据  分页查询
		    	PagerEntity page =new PagerEntity(a,1000);
		    	Map map =new HashMap();
		    	map.put("page", page.getStart());
		    	map.put("rows",page.getPageSize());
		    	
		    	String title ="学生列表";
		    	String[] rowName = {"学生ID","学生姓名"};
		    	//分页查询
		    	List<Map> list =studentMapper.findStudentList(map);
		    	
		    	//创建接受的数据
		    	List<Object[]> list1 =new ArrayList<Object[]>();
		    	Object[] obj=null;
		    	//循环遍历查询的数据然后把数据放到新的list集合中
		    	for (Map map1 : list) {
		    		//创建出序列的长度的object数组
					obj = new Object[rowName.length];
					obj[0]=map1.get("id");
					obj[1]=map1.get("name");
					list1.add(obj);
				}
		    	
		    	//5.
		    	ExportExcelMax exc =new ExportExcelMax(workbook, title, rowName, list1, response);
		    	try {
					exc.export(a);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }  
		   });  
		  }  
		  //6线程池停止
		  singleThreadExecutor.shutdown();
		  while(true){
			  //7.
			  if(singleThreadExecutor.isTerminated()){
				  String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";  
			        String headStr = "attachment; filename=\"" + fileName + "\"";  
			        response.setContentType("APPLICATION/OCTET-STREAM");  
			        response.setHeader("Content-Disposition", headStr);  
			        OutputStream out = response.getOutputStream();  
			        workbook.write(out);
			        ///8.
			        break;  
			  }
			  //9.
			  Thread.sleep(100);
		  }
		
		
	}
	
	

}
