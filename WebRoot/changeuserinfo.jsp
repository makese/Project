<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.css" rel="stylesheet" type="text/css">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

  </head>
  <body>
  <%
  	 UserInfo userInfo = (UserInfo)request.getAttribute("userInfo");
  	 ArrayList<Role> roles = (ArrayList)request.getAttribute("roles");
  	 Role role = (Role)request.getAttribute("role");
  %>
  <nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="#">系统管理</a> </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<%=basePath %>ManageServlet?token=main">首页</a></li>
        <li><a href="<%=basePath %>ManageServlet?token=employee">雇员详情</a></li>
        <li><a href="<%=basePath %>ManageServlet?token=userInfo">用户详情</a></li>
        <li><a href="<%=basePath %>ManageServlet?token=leave">请假详情</a></li>
      </ul>
    </div>
  </div>
</nav>
  <div class="container">
  <div class="row">
    <div class="col-md-3 pull-left">
      <div menuitemname="My Account" class="panel panel-default panel-actions">
        <div class="panel-heading">
          <h3 class="panel-title">&nbsp;修改用户信息 </h3>
        </div>
        <div class="list-group"> 
        	<a href="<%=basePath %>ManageServlet?token=userInfo" class="list-group-item active" > 返回 </a>	 	   
      </div>
    </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>修改用户信息</h1>
        <ol class="breadcrumb">
          <li>系统管理</li>
          <li>用户管理</li>
          <li class="active"> 修改用户信息 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <form method="post" action="UserInfoManage" >
      	<input type="hidden" name="userid" value="<%=userInfo.getUserID()%>"> 
        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label class="control-label">密码</label>
              <input type="text" name="password" id="inputpwd" value="<%=userInfo.getUserPsw()%>" class="form-control">
            </div>
		  </div>
          <div class="col-sm-6 col-xs-12 pull-right">
          <div class="form-group">
              <label for="inputRole" class="control-label">权限</label>
              <select name="role" id="inputRole" class="form-control"">
                <% for(int i = 0; i < roles.size(); i++) {%>
              	<option<%if(roles.get(i).getRoleID() == role.getRoleID()){%> selected<%} %> value="<%=roles.get(i).getRoleID() %>"><%=roles.get(i).getRole() %></option>
              <%}%>
              </select>
            </div>
          </div>
        </div>
        <div class="form-group text-center">
        <input class="btn btn-primary" type="submit" name="save" value="保存更改">
        <input class="btn btn-default" type="reset" onClick="window.location='<%=basePath %>ManageServlet?token=userInfo'" value="取消">
      </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
