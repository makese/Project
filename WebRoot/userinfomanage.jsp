<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'employee.jsp' starting page</title>
    
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
}
    	);
	} );
	</script>
	<script type="text/javascript">
	function invertSelectType() {
         var ids = $("input[name='operateID']");
         if ($("input[name='DeleteIDAll']").is(':checked')) {
             for (var i = 0; i < ids.length; i++) {
                 ids[i].checked = 'checked';
             }
         }
         else {
             for (var i = 0; i < ids.length; i++) {
                 ids[i].checked = "";
             }

         }
     }
	</script>
  </head>
  <body>
  <%
  		ArrayList<UserInfo> userInfos = (ArrayList<UserInfo>)request.getAttribute("userinfos");
  		ArrayList<Role> roleList = (ArrayList<Role>)request.getAttribute("rolelist");
  		ArrayList<Role> roles = (ArrayList<Role>)request.getAttribute("roles");
  		HashMap<String,Integer> number = (HashMap<String,Integer>) request.getAttribute("number");
   %>
   <nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand">系统管理</a> </div>
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
  <form name="form" action="UserInfoManage" method="get">
  <div class="row">
    <div class="col-md-3 pull-left sidebar">
      <div class="panel panel-default panel-actions view-filter-btns">
        <div class="panel-heading">
          <h3 class="panel-title"> <i class="fa fa-filter"></i>&nbsp;考勤 </h3>
        </div>
        <div class="list-group"> 
        <%for(int i = 0; i < roleList.size(); i++) {%>
        	<a class="list-group-item" > &nbsp; <span><%=roleList.get(i).getRole()%></span> &nbsp;<span class="badge"><%=number.get(roleList.get(i).getRole())%></span> </a> 
        <%}
        %>
        </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>用户管理</h1>
        <ol class="breadcrumb">
          <li> <a> 系统管理 </a> </li>
          <li class="active"> 用户管理 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <table id="example" class="table table-bordered table-hover">
        <thead>
          <tr >
            <th><input type="checkbox" name="DeleteIDAll" onclick="invertSelectType()" />账号</th>
            <th>密码</th>
            <th>权限</th>
          </tr>
        </thead>
        <tbody>
        <%for(int i = 0;  i < userInfos.size(); i++) {%>
        <tr>
            <td><input type="checkbox" name="operateID" value="<%=userInfos.get(i).getUserID()%>" /><%=userInfos.get(i).getUserID()%></td>
            <td><%=userInfos.get(i).getUserPsw()%></td>
            <td><%=roles.get(i).getRole()%></td>
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
        <div class="list-group">
        <a class="list-group-item" href="javascript:document:form.submit()">&nbsp;修改</a> 
        <a class="list-group-item">&nbsp;注销 </a> </div>
    </div>
  </div>
</div>
    </form>
</div>
  </body>
</html>
