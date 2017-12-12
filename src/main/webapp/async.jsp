<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
<script type="text/javascript">
var setting = {
		async: {
			enable: true,
			url:"<%=request.getContextPath()%>/treeController/asyncTreeList.do",
			autoParam:["id"]
		},
		data: {
			simpleData: {
				enable: true,
			    pIdKey:"pid",
			    idKey:"id",
			},
			key: {
				url: "xUrl"
			}
		},
		callback: {
			onClick: zTreeOnClick
		}
	};
	$(function(){
		$.fn.zTree.init($("#treeDemo111"), setting);
		$("#addAfterSaveBtn").hide();
	})
	
	function zTreeOnClick(event, treeId, treeNode){
		  // alert(event+","+treeId+","+treeNode.tId + ", " + treeNode.name);
		  ///获得父节点
		var node =treeNode.getParentNode();	
		//  alert(node.id);
		if(node!=null){
			$("#async_parentId").text(node.id);
			$("#async_parentName").text(node.name)
		}else{
			$("#pid").text("");
			$("#async_parentName").text("")
		}
		$("#name").val(treeNode.name);
		$("#icon").val(treeNode.icon);
		$("#target").val(treeNode.target);
		$("#url").val(treeNode.url);
		$("#id").val(treeNode.id);
		
	}
	
	

	
	function updateAfterSave(){
		//获得树形菜单所有的内容
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo111");
		//获得被选中菜单的属性
		var nodes = treeObj.getSelectedNodes();
		$.ajax({
			url:"<%=request.getContextPath()%>/treeController/updateAfterSave.do",
			type:"post",
			data:$("#async_form").serialize(),
			dataType:"json",
			success:function(tree){
				alert(tree);
			//  alert(nodes[0].name)
			nodes[0].name =tree.name;
			nodes[0].id=tree.id;
			nodes[0].pid=tree.pid;
			nodes[0].icon=tree.icon;
			nodes[0].url=tree.url;
			nodes[0].target=tree.target;
			treeObj.updateNode(nodes[0]);
			},
			error:function(){
				alert("修改失败");
			}
			
		})
	}
	
	
	function deleteTree(){
		//获得树形菜单所有的内容
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo111");
		//获得被选中菜单的属性
		var nodes = treeObj.getSelectedNodes();
		
	//	alert(nodes[0].id);
		$.ajax({
			url:"<%=request.getContextPath()%>/treeController/deleteTreeNodes.do",
			data:{"id":nodes[0].id},
			dataType:"json",
			type:"post",
			async:false,
			success:function(){
				treeObj.removeNode(nodes[0]);
				$("#pid").val("");
				$("#async_parentId").text("");
				$("#async_parentName").text("")
				$("#id").val("");
				$("#name").val("");
				$("#icon").val("");
				$("#url").val("");
				$("#target").val("");
			},
			error:function(){
				alert("删除成功");
			}
			
			
			
		})
		
	}
	
	function addTreeNode(){
		$("#updateAfterSaveBtn").hide();
		$("#addTreeBtn").hide();
		$("#deleteTreeBtn").hide();
		$("#addAfterSaveBtn").show();
		//获得树形菜单所有的内容
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo111");
		//获得被选中菜单的属性
		var nodes = treeObj.getSelectedNodes();
		if(nodes.length>0){
			$("#async_parentId").text(nodes[0].id);
			$("#pid").val("");
			$("#pid").val(nodes[0].id);
			$("#async_parentName").text(nodes[0].name);
	        $("#id").val("")		
			$("#name").val("");
			$("#icon").val("");
			$("#url").val("");
			$("#target").val("");
		}
	
		
	}
	
	function addAfterSave(){
		//获得树形菜单所有的内容
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo111");
		var nodes = treeObj.getSelectedNodes();
		$.ajax({
			url:"<%=request.getContextPath()%>/treeController/addAfterSave.do",
			data:$("#async_form").serialize(),
			type:"post",
			dataType:"json",
			success:function(treeData){
				if (nodes.length > 0) {
					treeObj.addNodes(nodes[0], treeData);
				} else {
					alert("1111");
					treeObj.addNodes(null, treeData);
				}
				$("#name").val("");
				$("#id").val("");
			    $("#icon").val("");
			    $("#url").val("");
				$("#target").val("");
				$("#type").val("");
				$("#async_parentId").text("");
				$("#pid").val("");
				$("#async_parentName").text("");
				$("#updateAfterSaveBtn").show();
				$("#addTreeBtn").show();
				$("#deleteTreeBtn").show();
				$("#addAfterSaveBtn").hide();
			},
			error:function(){
				alert("新增失败");
			}
		})
		
	}
</script>
<div style="border:1px red solid;width: 300px;height: 400px;margin-top: 10px;margin-left: 10px;float: left;">
   <div id="treeDemo111" class="ztree"></div>
</div>

 <div style="border: 1px green solid ;width: 500px;height: 400px;margin-top: 10px;margin-left: 10px;float: left;">
    <div style="margin-top: 3px">
       <input type="button" value="修改后保存" id="updateAfterSaveBtn" onclick="updateAfterSave()">
       <input type="button" value="新建菜单" id="addTreeBtn" onclick="addTreeNode()">
       <input type="button" value="删除菜单" id="deleteTreeBtn" onclick="deleteTree()">
       <input type="button" value="新建后保存" id="addAfterSaveBtn" onclick="addAfterSave()">
    </div>
    <div style="margin-top: 3px">
      <form id="async_form">
         &nbsp; &nbsp; &nbsp; &nbsp;父节点Id： &nbsp; &nbsp; &nbsp; &nbsp;<span id="async_parentId"></span>
           <input type="hidden" id="pid" name="pid">
         <br><br> 
          &nbsp; &nbsp; &nbsp; &nbsp;父节点名称： &nbsp; &nbsp; &nbsp; &nbsp;<span id="async_parentName">
          </span>
            <input type="hidden" id="id" name="id">
          <br><br>
         &nbsp; &nbsp; &nbsp; &nbsp;节点名称： &nbsp; &nbsp; <input type="text" id="name" name="name" width="188px"><br><br>
         &nbsp; &nbsp; &nbsp; &nbsp;节点图标：    &nbsp; &nbsp;<input type="text" id="icon" name="icon"  width="188px"><br><br>
    &nbsp; &nbsp; &nbsp; &nbsp;   URL： &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<input type="text" id="url" name="url"  width="188px"><br><br> 
       &nbsp; &nbsp; &nbsp; &nbsp;展开方式：<select id="target" name="target"  width="188px">
          <option value="_blank">_blank</option>
          <option value="_parent">_parent</option>
          <option value="_top">_top</option>
          <option value="_self">_self</option>
          <option value="main">main</option>
      </select><br><br>
      &nbsp; &nbsp; &nbsp; &nbsp; 菜单类型：<select id="type" name="type"  width="188px">
          <option value="1">菜单</option>
          <option value="2">功能</option>
      </select>
      </form>
    </div>
 
 
 </div>

</body>
</html>