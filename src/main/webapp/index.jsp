<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/inc.jsp"></jsp:include>
</head>
<body>
<script type="text/javascript">
$(function() {

		 var loginFun = function() {
			var loginTabs = $('#loginTabs').tabs('getSelected');//当前选中的tab
			var $form = loginTabs.find('form');//选中的tab里面的form
			if ($form.length == 1 && $form.form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post("<%=request.getContextPath()%>/userController/login.do", $form.serialize(), function(result) {
					if (result.success) {
						location.replace("<%=request.getContextPath()%>" + '/main.jsp');
					} else {
						$.messager.alert('提示', result.msg, 'error', function() {
							$('#loginBtn').linkbutton('enable');
						});
					}
				}, 'json');
			}
		};

		$('#loginDialog').show().dialog({
			modal : false,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				text : '注册',
				handler : function() {
					location.replace(sy.contextPath + '/reg.jsp');
				}
			}, {
				id : 'loginBtn',
				text : '登录',
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		});

		$ ('#userLoginCombobox').combobox({
			url : "<%=request.getContextPath()%>/userController/selectUserList.do",
			valueField : 'loginname',
			textField : 'loginname',
			required : true,
			panelHeight : 'auto',
			mode : 'remote',
			delay : 500
		});

		 $('#userLoginCombogrid').combogrid({
			 url : "<%=request.getContextPath()%>/userController/selectUserList.do",
			panelWidth : 500,
			panelHeight : 200,
			idField : 'loginname',
			textField : 'loginname',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'loginname',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'loginname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100,
				sortable : true
			}, {
				field : 'createdatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ]
		});  

	});
</script>
<%-- <strong>SSHE示例系统默认账户：</strong>
	<br />
	<br />
	<table class="table" border="1">
		<tr>
			<th>登录名/密码</th>
			<th>账户描述</th>
		</tr>
		<tr>
			<td>孙宇/123456</td>
			<td>超管，拥有系统所有权限</td>
		</tr>
		<tr>
			<td>guest/123456</td>
			<td>来宾用户，拥有查看权限</td>
		</tr>
		<tr>
			<td>admin1/123456</td>
			<td>资源管理员</td>
		</tr>
		<tr>
			<td>admin2/123456</td>
			<td>角色管理员</td>
		</tr>
		<tr>
			<td>admin3/123456</td>
			<td>机构管理员</td>
		</tr>
		<tr>
			<td>admin4/123456</td>
			<td>用户管理员</td>
		</tr>
		<tr>
			<td>admin5/123456</td>
			<td>系统监控管理员</td>
		</tr>
	</table>
	<br />
	<!-- <strong>如果登录不了，或者数据错乱、丢失等情况，请点击下面链接</strong> -->
	<br />
	<a href="<%=contextPath%>/init.jsp">初始化数据库(<%=contextPath%>/init.jsp)
	</a> --%>

	<div id="loginDialog" title="系统登录" style="display: none; width: 320px; height: 180px; overflow: hidden;">
		<div id="loginTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="用户输入模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input name="loginname" class="easyui-validatebox" data-options="required:true" value="123" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true" value="123" style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="自动补全模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input id="userLoginCombobox" name="loginname" type="text"  style="width: 214px;"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true"  style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div title="数据表格模式" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="50">登录名</th>
							<td><input id="userLoginCombogrid" name="name" type="text"  style="width: 214px;"></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true"  style="width: 210px;" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>