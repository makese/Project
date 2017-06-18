<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>

  </head>
  
<body>
<%Role role = (Role)session.getAttribute("role");  
  Employee employee = (Employee)session.getAttribute("employee");
  Position position = (Position)session.getAttribute("position");
  Work work = (Work)session.getAttribute("work");
  ArrayList<Integer> numbers = (ArrayList<Integer>)request.getAttribute("numbers");
%>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" >系统管理</a> </div>
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
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp; 你的信息</h3>
        </div>
        <div class="panel-body">
          <p><strong><%=employee.getName() %></strong></p>
          <p><%=employee.getSno() %><br/>
             <%=position.getJob() %><br/>
             <%=role.getRole() %>   <br/>
            <%=employee.getTel() %><br />
            <%=employee.getID() %></p>
        </div>
        <div class="panel-footer clearfix"> <a href="SalaryServlet" class="btn btn-success btn-sm btn-block">工资结算</a> </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>欢迎回来，<%=employee.getName()%></h1>
        <ul class="breadcrumb">
          <li><a>系统管理</a></li>
          <li class="active"><a>系统首页</a></li>
        </ul>
      </div>
    </div>
    <div class="col-md-9 pull-right main-content">
      <div class="tiles clearfix">
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>ManageServlet?token=employee'"> <a href="<%=basePath %>ManageServlet?token=employee">
          <div class="icon"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span></div>
          <div class="stat"><%=numbers.get(0)%></div>
          <div class="title">员工</div>
          <div class="highlight bg-color-blue"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>ManageServlet?token=userInfo'"> <a href="<%=basePath %>ManageServlet?token=userInfo">
          <div class="icon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
          <div class="stat"><%=numbers.get(1)%></div>
          <div class="title">用户</div>
          <div class="highlight bg-color-green"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>ManageServlet?token=leave'"> <a href="<%=basePath %>ManageServlet?token=leave">
          <div class="icon"><span class="glyphicon glyphicon-glass" aria-hidden="true"></span></div>
          <div class="stat"><%=numbers.get(3)%></div>
          <div class="title">请假请求</div>
          <div class="highlight bg-color-red"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile"> <a >
          <div class="icon"><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span></div>
          <div class="stat"><%=numbers.get(2)%></div>
          <div class="title">本月缺勤人数</div>
          <div class="highlight bg-color-gold"></div>
          </a> </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-3 pull-left">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"> <span class="glyphicon glyphicon-glass" aria-hidden="true"></span>&nbsp;请假请求 </h3>
        </div>
        <div class="list-group">
        <%  if(numbers.get(3) > 0) {%>
        <div class="list-group-item" > 有<%=numbers.get(3)%>未审核请假请求 </div>
        <%} else { %>
        <div class="list-group-item" > 没有未审核请假请求 </div>
        <%}
         %>
        </div>
        <div class="panel-footer clearfix"> <a href="<%=basePath %>ManageServlet?token=leave" class="btn btn-default btn-sm btn-block"> 请假详情 </a> </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"> <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;操作 </h3>
        </div>
        <div class="list-group"> <a href="<%=basePath %>main.jsp" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp; 返回人事工资管理系统 </a> <a menuItemName="Logout" href="LogoutServlet" class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Logout"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;注销 </a> </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="col-sm-6 pull-left">
        <div class="panel panel-default panel-accent-red">
          <div class="panel-heading">
            <h3 class="panel-title">
              <div class="pull-right"> <a href="<%=basePath %>ManageServlet?token=leave" class="btn btn-default bg-color-red btn-xs"> <i></i> 查看详情 </a> </div>
              <i></i>&nbsp; 请假请求 </h3>
          </div>
          <%if(numbers.get(3) == 0) {%>
          <div class="panel-body">
            <p>最近没有未审核请假请求</p>
          </div>
         <%}
         else {%>
         <div class="panel-body">
            <p>最近有<%=numbers.get(3) %>条未审核请假请求</p>
          </div>
         <%}%>
          <div class="panel-footer"> </div>
        </div>
      </div>
      <div class="col-sm-6 pull-right">
        <div class="panel panel-default panel-accent-gold">
          <div class="panel-heading">
            <h3 class="panel-title">
              <i></i>&nbsp;缺勤记录</h3>
          </div>
          <%
          	if(numbers.get(2) == 0) {%>
          	<div class="list-group"> <a class="list-group-item" id="leavework">最近没有缺勤记录<br />
            <span class="text-domain"></span> </a> </div>
          	<%}
          	else {%>
          	<div class="list-group"> <a class="list-group-item" id="leavework">最近有<%=numbers.get(2)%>人缺勤<br />
            <span class="text-domain"></span> </a> </div>
          	<%}
           %>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
