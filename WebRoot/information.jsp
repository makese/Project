<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'information.jsp' starting page</title>
    
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
  	 Employee employee = (Employee)session.getAttribute("employee");
  	 ArrayList<Role> roles = (ArrayList)request.getAttribute("roles");
  	 ArrayList<Position> positions = (ArrayList)request.getAttribute("positions");
  	 Role role = (Role)session.getAttribute("role");
  	 Position position = (Position)session.getAttribute("position");
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
      <div menuitemname="My Account" class="panel panel-default panel-actions">
        <div class="panel-heading">
          <h3 class="panel-title">&nbsp;我的帐号 </h3>
        </div>
        <div class="list-group"> 
            <a menuitemname="Change Password" href="<%=basePath %>password.jsp" class="list-group-item" id="Primary_Sidebar-My_Account-Change_Password"> 更改密码 </a> </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>我的详细信息</h1>
        <ol class="breadcrumb">
          <li>员工中心</li>
          <li class="active"> 员工详细信息 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <form method="post" action="InformationServlet" >
        <div class="row">
          <div class="col-sm-6">
            <div class="form-group">
              <label class="control-label">姓名</label>
              <input type="text" name="name" id="inputName" value="<%=employee.getName() %>" class="form-control">
            </div>
            <div class="form-group">
              <label class="control-label">性别</label>
              <input type="text" name="gender" id="inputGender" value="<%=employee.getGender() %>" class="form-control">
            </div>
          </div>
          <div class="col-sm-6 col-xs-12 pull-right">
            <div class="form-group">
              <label class="control-label">员工号</label>
              <input type="text" name="staffno" id="inputStaffNo" value="<%=employee.getSno() %> " readonly="readonly" class="form-control">
            </div>
            <div class="form-group">
              <label class="control-label">工资</label>
              <input type="text" name="salary" id="inputSalary" value="<%=employee.getSalary() %>" class="form-control">
            </div>
            <div class="form-group">
              <label class="control-label">身份证号</label>
              <input type="text" name="idnumber" id="inputIDNumber" value="<%=employee.getID() %>" class="form-control" required="required">
            </div>
            <div class="form-group">
              <label class="control-label">联系电话</label>
              <input type="tel" name="phonenumber" id="inputPhone" value="<%=employee.getTel() %>" class="form-control">
            </div>
          </div>
          <div class="col-sm-6 col-xs-12 pull-left">
            <div class="form-group">
              <label class="control-label">职位</label>
             <select name="position" id="inputPosition" class="form-control">
              <% for(int i = 0; i < positions.size(); i++) {%>
              <option <%if(positions.get(i).getPno() == position.getPno()){%> selected<%} %> value="<%=positions.get(i).getPno() %>"><%=positions.get(i).getJob() %></option>
              <%}%>
            </select>
            </div>
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
      </form>
    </div>
  </div>
</div>
</body>
</html>
