<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="userId" value="${userId}">
<input type="button" onclick="allotRole()" />
 <form id="roleform">
 </form>
 <script type="text/javascript">
 $(function(){
	  $.ajax({
		  url:"<%=request.getContextPath()%>/roleController/roleNodes.do",
		  data:{"userId":$("#userId").val()},
		  dataType:"json",
		  type:"post",
		  success:function(data){
			// alert(data); 
			 var str ="";
			 for (var i = 0; i < data.list.length; i++) {
				//alert(data.list[i].name)
				var count=0;
				for (var j = 0; j < data.roles.length; j++) {
					if(data.roles[j].id==data.list[i].id){
						str+="<input type='checkbox' name='rolecheck' checked='checked' value='"+data.roles[j].id+"'>"+data.roles[j].name+"";
						count=1;
						break;
					}
					
				}
				if(count!=1){
					str+="<input type='checkbox' name='rolecheck' value='"+data.list[i].id+"'>"+data.list[i].name+"";
				}
			}
			 $("#roleform").html(str);
			 
		  },error:function(){
			  alert("错误");
		  }
	  })
 })
 
 function allotRole(){
	var rlist =  document.getElementsByName("rolecheck");
	var rids = "";
	for (var i = 0; i < rlist.length; i++) {
		if(rlist[i].checked){
			if(rids== ""){
				rids = rlist[i].value;
			}else{
				rids = rlist[i].value +","+rids;
			}
			
		}
	}
	alert(rids);
	$.ajax({
		url:"<%=request.getContextPath()%>/roleController/allotRole.do",
		data:{"rids":rids,
			  "userId":$("#userId").val(),
			},
		type:"post",
		dataType:"json",
		success:function(data){
			alert("成功")
			$('#roledialog').dialog("close"); 
			
		},
		error:function(){
			
			alert("错误")
		}
	})
	 
 }
 
 </script>
</body>
</html>