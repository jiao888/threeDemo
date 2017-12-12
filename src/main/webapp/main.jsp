<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
 <!--  <frameset rows="20%,*">
    <frame  src="">
     <frameset cols="20%,*">
       <frame src="showTree.jsp">
       <frame  name="main" src="">
     </frameset>  
  </frameset> -->
  <jsp:include page="/inc.jsp"></jsp:include>
  <body class="easyui-layout">
    <div data-options="region:'north',title:'上边',split:true" style="height:100px;">
      	 登录名称：${user.loginname}
      	 <input type="hidden" id="oneid" value="${user.id}" >
    </div>   
    <div data-options="region:'south',title:'下边',split:true" style="height:100px;">
          <center>  金科教育官方后台网站</center>
    </div>   
    <div data-options="region:'west',title:'左边',split:true" style="width:180px;">
       <div id="aa" class="easyui-accordion" data-options="fit:true" style="width:300px;height:200px;">   
	    <div title="系统管理" data-options="selected:true" style="overflow:auto;padding:10px;">   
	        <div id="tree" class="ztree"></div>
	    </div>   
	    <div title="企业平台" data-options="" style="padding:10px;">   
	        content2    
	    </div>   
	    <div title="人力资源">   
	        content3    
	    </div>   
 </div>  
       
       
    </div>   
    <div data-options="region:'center',title:'主展示区'" style="padding:5px;">
		  <div id="tt" class="easyui-tabs" data-options="fit:true" style="width:500px;height:250px;">   
		    <div title="首页" >   
		         欢迎登录系统  
		    </div>   
		</div>  
          
    </div> 
    <script type="text/javascript">


    var setting = {
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
		alert($("#oneid").val())
		$.ajax({
			url:"<%=request.getContextPath()%>/treeController/queryTree2.do",
			type:"post",
			data:{"id":$("#oneid").val()},
			dataType:"json",
			async:true,
			success:function(data){
				$.fn.zTree.init($("#tree"), setting, data);
			},
			error:function(){
				alert("查询失败，请联系110");
			}
		})
	})
	
	function zTreeOnClick(event, treeId, treeNode) {
    //	alert(treeNode.url);
		//获得树形菜单的所有节点
		//var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var tabs =$("#tt").tabs("getTab",treeNode.name);
		if(tabs==null){
			if(treeNode.url!=null &&treeNode.url!=""){
				$("#tt").tabs("add",{
				    title:treeNode.name,
				    href:"<%=request.getContextPath()%>/"+treeNode.url,
				    closable:true,    
				    tools:[{    
				        iconCls:'icon-mini-refresh',    
				        handler:function(){    
				            
				        }    
				    }]    

			})
		 }
		}else{
			   //如果选项卡已激活需要切换选项卡切换到已激活的选项卡上
			   $("#tt").tabs("select",treeNode.name);
			   var tabs_selected =$("#tt").tabs("getSelected");
			   tabs_selected.panel("refresh","<%=request.getContextPath()%>/"+treeNode.url);
			  
		  }

		//获得父节点
		

	
	};
    
	
    
    </script>
</body>
</html>