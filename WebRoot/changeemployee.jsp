<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">
<%
Employee employee = (Employee)request.getAttribute("employee");
ArrayList<Position> positions = (ArrayList)request.getAttribute("positions");
Position position = (Position)request.getAttribute("position");
%>
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
      <div class="panel panel-default panel-actions">
        <div class="panel-heading">
          <h3 class="panel-title">&nbsp;修改雇员 </h3>
        </div>
        <div class="list-group"> 
        	<a href="<%=basePath %>ManageServlet?token=employee" class="list-group-item active" > 返回 </a>	 	   
      </div>
    </div>
   </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>修改雇员信息</h1>
        <ol class="breadcrumb">
          <li>系统管理 </li>
          <li>雇员管理</li>
          <li class="active"> 修改雇员信息 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
    <form method="post" action="EmployeeManage" >
    <input type="hidden" name="token" value="修改">
      <input type="hidden" name="sno" value="<%=employee.getSno() %>">
      <div class="row">
        <div class="col-sm-6">
          <div class="form-group">
            <label class="control-label">姓名</label>
			<input type="text" name="name" id="inputName" value="<%=employee.getName() %>" class="form-control" />
          </div>
          <div class="form-group">
            <label class="control-label">性别</label>
            <select name="gender" id="inputGender" class="form-control"">
              	<option <%if(employee.getGender().trim().equals("男")){%> selected<%} %> value="男">男</option>
              	<option <%if(employee.getGender().trim().equals("女")){%> selected<%} %> value="女">女</option>
              </select>
          </div>
        </div>
        <div class="col-sm-6 col-xs-12 pull-right">
          <div class="form-group">
            <label class="control-label">员工号</label>
            <input type="text" name="staffno" id="inputStaffNo" value="<%=employee.getSno() %> " readonly="readonly" class="form-control" />
          </div>
          <div class="form-group">
            <label class="control-label">工资</label>
            <input type="text" name="salary" id="inputSalary" value="<%=employee.getSalary() %>" class="form-control" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" />
          </div>
          <div class="form-group">
            <label class="control-label">身份证号</label>
            <input type="text" name="idnumber" id="inputIDNumber" value="<%=employee.getID() %>" class="form-control" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" />
          </div>
          <div class="form-group">
            <label class="control-label">联系电话</label>
            <input type="tel" name="phonenumber" id="inputPhone" value="<%=employee.getTel() %>" class="form-control" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" />
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
        </div>
      </div>
      <div class="form-group text-center">
        <input class="btn btn-primary" type="submit" name="save" value="保存更改">
        <input class="btn btn-default" type="reset" onClick="window.location='<%=basePath %>ManageServlet?token=employee'" value="取消">
      </div>
      </div>
    </form>
  </div>
</div>
</div>
</body>
</html>
