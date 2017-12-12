/** 
 * <pre>项目名称:ssm-ztree 
 * 文件名称:TreeController.java 
 * 包名:com.jk.controller 
 * 创建日期:2017年11月17日下午2:19:08 
 * Copyright (c) 2017,yangpei310@163.com All Rights Reserved.</pre> 
 */  
package com.jk.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jk.model.Tree;
import com.jk.service.TreeService;
import com.jk.until.ExportExcel;

/** 
 * <pre>项目名称：ssm-ztree    
 * 类名称：TreeController    
 * 类描述：    
 * 创建人：朱义龙    
 * 创建时间：2017年11月17日 下午2:19:08    
 * 修改人：朱义龙    
 * 修改时间：2017年11月17日 下午2:19:08    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/treeController")
public class TreeController extends BaseController implements ServletContextAware{

	@Autowired
	private TreeService treeService;
	
	
	private ServletContext servletContext;
	


	@RequestMapping("queryTree")
	@ResponseBody
	public List<Tree> queryTree(Tree tree){
		List<Tree> list =new ArrayList<Tree>();
		try {
			list = treeService.queryTree(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("queryTree2")
	@ResponseBody
	public List<Tree> queryTree2(Tree tree,String id){
		int a = (int)(Math.random()*(9999-1000+1))+1000;
		List<Tree> list =new ArrayList<Tree>();
		try {
			list = treeService.queryTree2(tree,id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping("asyncTreeList")
	@ResponseBody
	public List<Tree> asyncTreeList(Tree tree){
		List<Tree> list =new ArrayList<Tree>();
		try {
			list =treeService.asyncTreeList(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping("updateAfterSave")
	@ResponseBody
	public Tree updateAfterSave(Tree tree){
		try {
			treeService.updateAfterSave(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tree;
	}
	
	@RequestMapping("deleteTreeNodes")
	@ResponseBody
	public Tree deleteTreeNodes(Tree tree){
		try {
			treeService.deleteTreeNodes(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tree;
	}
	
	@RequestMapping("addAfterSave")
	@ResponseBody
	public Tree addAfterSave(Tree tree){
		try {
			treeService.addAfterSave(tree);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tree;
	}
	
	@RequestMapping("exportExcel")
	public void exportExcel(Tree tree,HttpServletResponse response){
		List<Tree> list =new ArrayList<Tree>();
		try {
			String title = "树形菜单";
			String[] rowName = {"id","pid","名称","图标","target","路径"};
			list = treeService.queryTree(tree);
			List<Object[]> arrObj=new ArrayList<Object[]>();
			Object[] objs=null;
			for (int i = 0; i < list.size(); i++) {  
			    Tree tree1 = list.get(i);  
			    objs = new Object[rowName.length];  
			    objs[0] = tree1.getId();  
			    objs[1] = tree1.getPid();  
			    objs[2] = tree1.getName();  
			    objs[3] = tree1.getIcon();  
			    objs[4] = tree1.getTarget();  
			    objs[5] = tree1.getUrl();  
			  /*  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    String date = df.format(man.getModiDate());  
			    objs[5] = date;  */
			    arrObj.add(objs);  
			}  
			ExportExcel  exportExcel = new ExportExcel(title, rowName, arrObj, response);
			exportExcel.export();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("exportExcels")
	public void exportExcels(Tree tree,String checks,HttpServletResponse response){
		
//		System.out.println(checks);
		List<Tree> list =new ArrayList<Tree>();
		String title = "树形菜单";
		if(checks==null || "".equals(checks)){
			checks= "id,pid,名称,icon,target,url";
		}
		try {
			String rowNames =new String(checks.getBytes("ISO-8859-1"),"utf-8");
			String name =new String(tree.getName().getBytes("ISO-8859-1"),"utf-8");
			tree.setName(name);
			String[] rowName = rowNames.split(",");
			list = treeService.queryTree(tree);
			List<Object[]> arrObj=new ArrayList<Object[]>();
			Object[] objs=null;
			for (int i = 0; i < list.size(); i++) {  
			    Tree tree1 = list.get(i);  
			    objs = new Object[rowName.length];  
			    for (int j = 0; j < rowName.length; j++) {
			    	if(rowName[j].equals("id")){
			    		objs[j] = tree1.getId();
			    	}
			    	if(rowName[j].equals("pid")){
			    		objs[j] = tree1.getPid();
			    	}  
			    	if(rowName[j].equals("名称")){
			    		objs[j] = tree1.getName();
			    	}
			    	if(rowName[j].equals("icon")){
			    		objs[j] = tree1.getIcon();
			    	}
			    	if(rowName[j].equals("target")){
			    		objs[j] = tree1.getTarget();
			    	}
			    	if(rowName[j].equals("url")){
			    		objs[j] = tree1.getUrl();
			    	}
				}
			  
			  /*  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			    String date = df.format(man.getModiDate());  
			    objs[5] = date;  */
			    arrObj.add(objs);  
			}  
			ExportExcel  exportExcel = new ExportExcel(title, rowName, arrObj, response);
			exportExcel.export();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("importExcel")
	public void importExcel(@RequestParam("file") CommonsMultipartFile file,HttpServletResponse response){
		if(!file.isEmpty()){
			//项目一个相对路径
			String realPath = this.servletContext.getRealPath("/upload/");
			//获得文件的名称
			String originalFilename = file.getOriginalFilename();
			//获得后缀名
			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
			//重命名文件
			String imgUrl = new Date().getTime()+fileType;
			//创建一个新的文件
			File file2 =new File(realPath, imgUrl);
			
			try {
				//把上传的文件放到新文件
				file.getFileItem().write(file2);
				//把新的文件的地址
				String path = realPath+"\\"+imgUrl;
				//创建一个list接受
				List<Tree> list =new ArrayList<Tree>();
				//创建一个新的poi对象
				HSSFWorkbook hssfWorkbook =new HSSFWorkbook(new FileInputStream(new File(path)));
				//获得所有sheet页进行循环遍历getNumberOfSheets() 获得对象中的所有sheet
				for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
					//获得每一个sheet对象
					HSSFSheet sheetAt = hssfWorkbook.getSheetAt(i);
					//获得每一个sheet中的行 getPhysicalNumberOfRows  获得每一行
					for (int j = 3; j < sheetAt.getPhysicalNumberOfRows(); j++) {
						//获得每一个行对象
						HSSFRow row = sheetAt.getRow(j);
						Tree t =new Tree();
						if(row.getCell(2)!=null&& !"".equals(row.getCell(2))){
							
							t.setName(TreeController.getCellValue(row.getCell(2)));
						}
						if(row.getCell(3)!=null&& !"".equals(row.getCell(3))){
							
							t.setIcon(TreeController.getCellValue(row.getCell(3)));
						}
						if(row.getCell(4)!=null&& !"".equals(row.getCell(4))){
							
							t.setTarget(TreeController.getCellValue(row.getCell(4)));
						}
						if(row.getCell(5)!=null&& !"".equals(row.getCell(5))){
							
							t.setUrl(TreeController.getCellValue(row.getCell(5)));
						}
						list.add(t);
					}
				}
				treeService.insertMany(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//判断从Excel文件中解析出来数据的格式   
    private static String getCellValue(HSSFCell cell){   
        String value = null;   
        //简单的查检列类型   
        switch(cell.getCellType())   
        {   
            case HSSFCell.CELL_TYPE_STRING://字符串   
                value = cell.getRichStringCellValue().getString();   
                break;   
            case HSSFCell.CELL_TYPE_NUMERIC://数字   
                long dd = (long)cell.getNumericCellValue();   
                value = dd+"";   
                break;   
            case HSSFCell.CELL_TYPE_BLANK:   
                value = "";   
                break;      
            case HSSFCell.CELL_TYPE_FORMULA:   
                value = String.valueOf(cell.getCellFormula());   
                break;   
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值   
                value = String.valueOf(cell.getBooleanCellValue());   
                break;   
            case HSSFCell.CELL_TYPE_ERROR:   
                value = String.valueOf(cell.getErrorCellValue());   
                break;   
            default:   
                break;   
        }   
         return value;   
     }


	/* (non-Javadoc)    
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)    
	 */
	@Override
	public void setServletContext(ServletContext servletContext) {
                 this.servletContext=servletContext;		
	}

	
}
