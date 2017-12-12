<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <table id="dataTable"></table> 
 <div id="roledialog"></div>
  
<script type="text/javascript">
$(function(){
	   $('#dataTable').datagrid({    
		    url:'<%=request.getContextPath()%>/userController/selectUserList.do', 
		    fitColumns:true,
		    ctrlSelect:true,
		    columns:[[    
		        {field:'id',title:'序号',width:100},    
		        {field:'name',title:'姓名',width:100},    
		        {field:'age',title:'年龄',width:100},    
		        {field:'sex',title:'性别',width:100},    
		        {field:'loginname',title:'登录名',width:100,align:'right'},    
		        {
		        	field:'11',
		        	title:'操作',
		        	width:100,
		        	formatter:function(value,row,index){
		        		return "<input type='button' value='分配角色' onclick='setRole("+row.id+")'>";
		        	}
		        }    
		    ]]    
		});  
})

function setRole(id){
	
	$("#roledialog").dialog({
	    title: '分配角色',    
	    width: 400,    
	    height: 200,    
	    closed: false,    
	    cache: false,    
	    href: '<%=request.getContextPath()%>/roleController/toShowRole.do?userId='+id,    
   })
}
</script>
</body>
</html>