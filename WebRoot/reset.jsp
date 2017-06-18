<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reset.jsp' starting page</title>
    
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
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand">人事工资系统</a> </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a >首页</a></li>
        <li><a >工资详情</a></li>
        <li><a >考勤详情</a></li>
        <li><a >请假详情</a></li>
        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">账号<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=basePath%>login.jsp">登陆</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="<%=basePath %>reset.jsp">忘记密码</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
  <div class="row">
    <div class="col-xs-12 main-content">
      <div class="logincontainer">
        <div class="header-lined">
          <h1>密码重置</h1>
        </div>
        <p>忘记您的密码？输入您的身份证信息，下面开始重置的过程。</p>
        <form method="post" action="<%=basePath%>ResetServlet" role="form">
          <input type="hidden" name="token" value="c70c9f4d457f22942a84442af1298a216688b7fb">
          <input type="hidden" name="action" value="reset">
          <div class="form-group">
            <label for="inputIDNumber">身份证号码</label>
            <input type="text" name="ID" class="form-control" id="inputIDNumber" placeholder="号码" autofocus>
          </div>
          <div class="form-group text-center">
            <button type="submit" class="btn btn-primary">提交</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
