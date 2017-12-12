<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table id="datatreeTable"></table> 
 	<div id="treedialog"></div>
 	
<script type="text/javascript">
$(function(){
	   $('#datatreeTable').datagrid({    
		    url:'<%=request.getContextPath()%>/roleController/selectRoleList.do', 
		    fitColumns:true,
		    ctrlSelect:true,
		    columns:[[    
		        {field:'id',title:'序号',width:100},    
		        {field:'description',title:'权限',width:100},    
		        {field:'iconcls',title:'不知道',width:100},    
		        {field:'name',title:'角色',width:100},    
		        {
		        	field:'11',
		        	title:'操作',
		        	width:100,
		        	formatter:function(value,row,index){
		        		return "<input type='button' value='赋权' onclick='settreeRole("+row.id+")'>";
		        	}
		        }    
		    ]]    
		});  
})
function settreeRole(id){
	$("#treedialog").dialog({
	    title: '分配权限',    
	    width: 400,    
	    height: 200,    
	    closed: false,    
	    cache: false,    
	    href: '<%=request.getContextPath()%>/roleController/tosettree.do?roleId='+id, 
	    buttons:[{
			text:'保存',
			handler:function(){
				var treeObj = $.fn.zTree.getZTreeObj("settree");
				var nodes = treeObj.getCheckedNodes(true);
				var roleids = "";
				for (var v = 0; v < nodes.length; v++) {
					if(roleids== ""){
						roleids = nodes[v].id;
					}else{
						roleids = nodes[v].id +","+roleids;
					}
				}
				alert(roleids);
				$.ajax({
					url:"<%=request.getContextPath()%>/roleController/setTreeChange.do",
					type:"post",
					dataType:"json",
					data:{"treeids":roleids},
					success:function(result){
						alert(result)
						$('#treedialog').dialog("close"); 
						
						location.href="<%=request.getContextPath()%>/main.jsp";
					}
				})
				
				
				
			}
		},{
			text:'关闭',
			handler:function(){
				$('#treedialog').dialog("close"); 
			}
		}]
		
	    		
   })
	
}
</script>
</body>
</html>