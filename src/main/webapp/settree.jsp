<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div id="settree" class="ztree"></div>

<script type="text/javascript">
var setting = {
		check: {
            enable: true,
            chkStyle : "checkbox"
        },
		data: {
			simpleData: {
				enable: true,
			    pIdKey:"pid",
			    idKey:"id",
			},key: {
				url: "xUrl"
			}
		},callback: {
			onClick: zTreeOnClick
		}
	};
$(function(){
	$.ajax({
		url:"<%=request.getContextPath()%>/treeController/queryTree.do",
		type:"post",
		dataType:"json",
		async:true,
		success:function(data){
			$.fn.zTree.init($("#settree"), setting, data);
			$.ajax({
				url:"<%=request.getContextPath()%>/roleController/querysettree.do",
				type:"post",
				dataType:"json",
				success:function(result){
					for (var i = 0; i < result.length; i++){
						SelectNode(result[i].id);
					}
				}
			})
		},
		error:function(){
			alert("查询失败，请联系110");
		}
	})
})

function SelectNode(id) { 
	//alert(id)
			var treeObj = $.fn.zTree.getZTreeObj("settree");
			var treenode = treeObj.getNodeByParam("id",id,null);
			treeObj.checkNode(treenode, true, false);
			//alert(treenode);
			console.info(treenode);
//             var treeObj = $.fn.zTree.getZTreeObj("settree");  
//             var treenode = treeObj.getNodeByParam("id", 1, null);  
//             treeObj.expandNode(treenode, true, true, true);  
//             treeObj.selectNode(treenode);  
 }



	
	

</script>
</body>
</html>