<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP  starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
    	$('#example').DataTable({
    language: {
        "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
        },
        "oAria": {
            "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
        }
    }
});
	} );
</script>

  </head>
  
  <body>
  <%
  		ArrayList<Leave> leaves = (ArrayList<Leave>)request.getAttribute("leaves");
  		Employee employee = (Employee)request.getAttribute("employee");
  		HashMap<String,Integer> number = (HashMap<String,Integer>) request.getAttribute("number");
   %>
   <nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" >人事工资系统</a> </div>
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
    <div class="col-md-3 pull-left sidebar">
      <div class="panel panel-default panel-actions view-filter-btns">
        <div class="panel-heading">
          <h3 class="panel-title"> <i class="fa fa-filter"></i>&nbsp;请假</h3>
        </div>
        <div class="list-group"> 
        	<a class="list-group-item" > &nbsp; <span>未审核</span> &nbsp;<span class="badge"><%=number.get("未审核")%></span> </a>
        	<a class="list-group-item" > &nbsp; <span>允许</span> &nbsp;<span class="badge"><%=number.get("允许")%></span> </a> 
        	<a class="list-group-item" > &nbsp; <span>拒绝</span> &nbsp;<span class="badge"><%=number.get("拒绝")%></span> </a>  
        </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>请假信息</h1>
        <ol class="breadcrumb">
          <li> <a > 员工中心 </a> </li>
          <li class="active"> 请假信息 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <table id="example" class="table table-bordered table-hover">
        <thead>
          <tr >
            <th><input type="checkbox" name="DeleteIDAll" onclick="invertSelectType()" />员工号</th>
            <th>姓名</th>
            <th>请假日期</th>
            <th>请假天数</th>
            <th>请假理由</th>
            <th>状态</th>
          </tr>
        </thead>
        <tbody>
        <%for(int i = 0;  i < leaves.size(); i++) {%>
        	<tr>
            <td><input type="checkbox" name="operateID" value="<%=leaves.get(i).getLno()%>" /><%=leaves.get(i).getSno() %></td>
            <td><%=employee.getName() %></td>
            <td><%=leaves.get(i).getDate()%></td>
            <td><%=leaves.get(i).getDay() %></td>
            <td style="word-wrap:break-word;word-break:break-all;"><%=leaves.get(i).getReason() %></td>
            <td><%=leaves.get(i).getState() %></td> 
          </tr>
        <%}
         %>
        </tbody>
      </table>
    </div>
    <div class="col-md-3 pull-left">
      <div class="panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title"> <i class="fa fa-bookmark"></i>&nbsp;操作 </h3>
        </div>
       <div class="list-group"> <a  href="<%=basePath %>ManageServlet?token=main" class="list-group-item" >&nbsp;进入系统管理界面</a> <a menuItemName="Logout" href="LogoutServlet" class="list-group-item" id="Secondary_Sidebar-Client_Shortcuts-Logout"> <i class="fa fa-arrow-left fa-fw"></i>&nbsp;注销 </a> </div>
  </div>
</div>
</div>
  </body>
</html>
