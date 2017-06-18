<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!Doctype html>
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>请登录</title>
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function seeE() {
    document.getElementById('vadimg').src='./image.jsp?t='+new Date().getTime();
  }
	</script>
  </head>
  
<body>
<%  
    String message=(String)request.getParameter("message");
    if(message!=null){
       if(message.equals("1")){%>
     <script type="text/javascript">
     alert("账号密码错误！！！");
     </script>
    <%} else {%>
    <script type="text/javascript">
    alert("验证码填写错误！！！");
    </script>
   <%} }%>
   
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand">人事工资系统</a> </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a>首页</a></li>
        <li><a>工资详情</a></li>
        <li><a>考勤详情</a></li>
        <li><a>请假详情</a></li>
        <li class="dropdown"> <a  class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">账号<span class="caret"></span></a>
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
          <h1>登录</h1>
        </div>
        <form method="post" action="<%=basePath %>LoginServlet" role="form">
          <div class="form-group">
            <label for="inputName">登陆账号</label>
            <input type="text" name="username" class="form-control" id="inputName" placeholder="账号" autofocus>
          </div>
          <div class="form-group">
            <label for="inputPassword">密码</label>
            <input type="password" name="password" class="form-control" id="inputPassword" placeholder="密码" autocomplete="off">
          </div>
          <div class="form-group">
            <label for="datafield">验证码</label>
            <div>
            <input type="text" name="code" class="form-control" id="inputcode" placeholder="验证码" autofocus>
            <img border=0 id="vadimg" src="image.jsp" onclick="seeE();return false;">
            </div>
          </div>
          <div align="center">
            <input name="submit" id="login" type="submit" class="btn btn-primary" value="登录">
            <a href="<%=basePath %>reset.jsp" class="btn btn-default">忘记密码</a> 
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
