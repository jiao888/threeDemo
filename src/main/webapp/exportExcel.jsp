<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.8.0.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/artDialog/css/ui-dialog.css">
<script src="<%=request.getContextPath()%>/js/artDialog/dist/dialog-plus-min.js"></script>
</head>
<script type="text/javascript">
   $(function(){
	   queryTreeList();
   })

   function queryTreeList(){
	   $.ajax({
		   url:"<%=request.getContextPath()%>/treeController/queryTree.do",
		   dataType:"json",
		   data:$("#index_form").serialize(),
		   type:"post",
		   success:function(data){
			   var str="<tr><td>id</td><td>pid</td><td>name</td><td>icon</td><td>target</td><td>url</td></tr>"
			   for (var i = 0; i < data.length; i++) {
			   str+="<tr><td>"+data[i].id+"</td><td>"+data[i].pid+"</td><td>"+data[i].name+"</td><td>"+data[i].icon+"</td><td>"+data[i].target+"</td><td>"+data[i].url+"</td></tr>";
			  }
			  $("#tree").html(str);
		   },error:function(){
			   alert("查询失败");
		   }
	   })
   }
   
   function exportExcels(){
	   var data =$("#index_form").serialize();
	   location.href="<%=request.getContextPath()%>/treeController/exportExcels.do?"+data;
	 <%--   $.ajax({
		   url:"<%=request.getContextPath()%>/treeController/exportExcels.do",
		   data:$("#index_form").serialize(),
		   type:"post",
		   dataType:"json",
		   success:function(){
			   alert("导出成功")
		   },
		   error:function(){
			   alert("导出失败");
		   }
	   }) --%>
   }
   
   function importExcel(){
	   var login_form=dialog({
			  title:"新增",
			  content:$("#index_dialog").html($("#index_dialog").html()),
		  });
		  login_form.showModal();
   }

</script>
<body>
<div>
   <form id="index_form">
      名称：<input type="text" name="name"> 
      <input type="checkbox" name="checks" value="id">id
      <input type="checkbox" name="checks" value="pid">pid
      <input type="checkbox" name="checks" value="名称">name
      <input type="checkbox" name="checks" value="icon">icon
      <input type="checkbox" name="checks" value="target">target
      <input type="checkbox" name="checks" value="url">url
      <input type="button"  value="查询" onclick="queryTreeList()">
      <input type="button" value="导出Excel" onclick="exportExcels()">
      <input type="button" value="导入Excel" onclick="importExcel()">
   
   </form>

</div>
  

 <div>
    <table id="tree" border="1px"> </table>
  </div> 
  
  <div id="index_dialog" style="display: none">
     <form action="<%=request.getContextPath()%>/treeController/importExcel.do" enctype="multipart/form-data" method="post">
       <input type="file" name="file">
       <input type="submit" value="提交">
     
     </form>
  </div>
  
  
</body>
</html>