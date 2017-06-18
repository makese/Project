<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人事工资系统</title>
    
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
  Integer signIn = (Integer)session.getAttribute("signin");
  ArrayList<Leave> leaves = (ArrayList)session.getAttribute("leaves");
%>
<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand">人事工资系统</a> </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="<%=basePath %>main.jsp">首页</a></li>
        <li><a href="<%=basePath %>Salary.jsp">工资详情</a></li>
        <li><a href="<%=basePath %>WorkServlet">考勤详情</a></li>
        <li><a href="<%=basePath %>LeaveServlet">请假详情</a></li>
        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><%=employee.getName() %> <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<%=basePath %>InformationServlet">详细信息</a></li>
            <li><a href="<%=basePath %>password.jsp">修改密码</a></li>
            <li role="separator" class="divider"></li>
            <li><a href="LogoutServlet">注销</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
  <div class="row">
    <div class="col-md-3 pull-left">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><span class="glyphicon glyphicon-user" aria-hidden="true">&nbsp;你的信息</h3>
        </div>
        <div class="panel-body">
          <p><strong><%=employee.getName() %></strong></p>
          <p><%=employee.getSno() %><br/>
             <%=position.getJob() %><br/>
             <%=role.getRole() %>   <br/>
            <%=employee.getTel() %><br />
            <%=employee.getID() %></p>
        </div>
          <div class="panel-footer clearfix active">
        <%
        	if(signIn == 1) {%>
        	<a href="SignInServlet" class="btn btn-success btn-sm btn-block">签到</a>
        	<%}
        	else { %>
        	 <a class="btn btn-success btn-sm btn-block active">签到</a> 
        	<%}%>
        </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>欢迎回来，<%=employee.getName()%></h1>
        <ul class="breadcrumb">
          <li><a>员工中心</a></li>
          <li class="active"><a>员工首页</a></li>
        </ul>
      </div>
    </div>
    <div class="col-md-9 pull-right main-content">
      <div class="tiles clearfix">
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>Salary.jsp'"> <a href="<%=basePath %>Salary.jsp">
          <div class="icon"><span class="glyphicon glyphicon-jpy" aria-hidden="true"></div>
          <div class="stat"><%=employee.getSalary() %></div>
          <div class="title">工资</div>
          <div class="highlight bg-color-blue"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>WorkServlet'"> <a href="<%=basePath %>WorkServlet">
          <div class="icon"><span class="glyphicon glyphicon-credit-card" aria-hidden="true"></span></div>
          <div class="stat"><%=work.getWorkDay() %></div>
          <div class="title">工作天数</div>
          <div class="highlight bg-color-green"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>WorkServlet'"> <a href="<%=basePath %>WorkServlet">
          <div class="icon"><span class="glyphicon glyphicon-fire" aria-hidden="true"></div>
          <div class="stat"><%=work.getOverDay() %></div>
          <div class="title">加班天数</div>
          <div class="highlight bg-color-red"></div>
          </a> </div>
        <div class="col-sm-3 col-xs-6 tile" onClick="window.location='<%=basePath %>WorkServlet'"> <a href="<%=basePath %>WorkServlet">
          <div class="icon"><span class="glyphicon glyphicon-glass" aria-hidden="true"></div>
          <div class="stat"><%=position.getLeaveday()-work.getAbsence() %></div>
          <div class="title">剩余请假天数</div>
          <div class="highlight bg-color-gold"></div>
          </a> </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-md-3 pull-left">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><span class="glyphicon glyphicon-glass" aria-hidden="true"></span>&nbsp;请假请求 </h3>
        </div>
        <div class="list-group">
        <% if(leaves.size() > 0) {%>
        	<div class="list-group-item" id="Secondary_Sidebar-Client_Contacts-No_Contacts"> 有<%=leaves.size()%>条请假请求 </div>
        <%}
        else {%>
        	<div class="list-group-item" id="Secondary_Sidebar-Client_Contacts-No_Contacts"> 没有请假请求 </div>
        <%}%>
        </div>
        <div class="panel-footer clearfix"> <a href="" class="btn btn-default btn-sm btn-block"  data-toggle="modal" data-target="#leave"> 申请请假 </a> </div>
        <div class="modal fade" id="leave" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="ModalLabel">请假申请</h4>
              </div>
              <form method="post" action="LeaveServlet" >
              <div class="modal-body">
                  <div class="form-group">
                    <label for="leave-date" class="control-label">请假时间(例2012-8-4):</label>
                    <input type="text" class="form-control" name="date" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" />
                  </div>
                  <div class="form-group">
                    <label for="recipient-name" class="control-label">请假天数:</label>
                    <input type="text" class="form-control" name="day" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="this.v();" />
                  </div>
                  <div class="form-group">
                    <label for="leave-reason" class="control-label">请假理由:</label>
                    <textarea class="form-control" name="reason"></textarea>
                  </div>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary">申请</button>
              </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"><span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;操作 </h3>
        </div>
        <div class="list-group"> <a href="<%=basePath %>ManageServlet?token=main" class="list-group-item"> <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>&nbsp; 进入系统管理 </a> <a menuItemName="Logout" href="LogoutServlet" class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Logout"> <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>&nbsp;注销 </a> </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="col-sm-6 pull-left">
        <div class="panel panel-default panel-accent-red">
          <div class="panel-heading">
            <h3 class="panel-title">
              <div class="pull-right"> <a href="<%=basePath %>LeaveServlet" class="btn btn-default bg-color-red btn-xs"> <i></i> 查看详情 </a> </div>
              <i></i>&nbsp; 请假请求 </h3>
          </div>
          <div class="panel-body">
            <% if(leaves.size() > 0) {%>
        	<p>有<%=leaves.size()%>条请假请求<p>
        <%}
        else {%>
        	<p> 没有请假请求</p>
        <%}%>
          </div>
          <div class="panel-footer"> </div>
        </div>
      </div>
      <div class="col-sm-6 pull-right">
        <div class="panel panel-default panel-accent-gold">
          <div class="panel-heading">
            <h3 class="panel-title">
              <div class="pull-right"> <a href="<%=basePath %>WorkServlet" class="btn btn-default bg-color-gold btn-xs"> <i></i> 查看全部 </a> </div>
              <i></i>&nbsp;缺勤记录</h3>
          </div>
          <%if(work.getLackDay() > 0) {%>
          <div class="list-group"> <a shref="<%=basePath %>WorkServlet" class="list-group-item" id="lackwork">最近有<%=work.getLackDay() %>条缺勤记录<br />
            <span class="text-domain"></span> </a> </div>
          <%} else { %>
          <div class="list-group"> <a shref="<%=basePath %>WorkServlet" class="list-group-item" id="lackwork">最近没有缺勤记录<br />
            <span class="text-domain"></span> </a> </div>
          <%}%>
          <div class="panel-footer"> </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
