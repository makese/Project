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
});
	} );
	</script>
    <script type="text/javascript">
    function submitAdd() {
     			var a = document.getElementsByName("token")[0];
     			a.value="添加";
     			form1.submit();
     		}
    function submitChange() {
     			var a = document.getElementsByName("token")[0];
     			a.value="修改";
     			form1.submit();
     		}
    function submitDelete() {
     			var a = document.getElementsByName("token")[0];
     			a.value="删除";
     			form1.submit();
     		}
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
  		ArrayList<Employee> employees = (ArrayList<Employee>)request.getAttribute("employees");
  		ArrayList<Position> positionList = (ArrayList<Position>)request.getAttribute("positionlist");
  		ArrayList<Position> positions = (ArrayList<Position>)request.getAttribute("positions");
  		HashMap<String,Integer> number = (HashMap<String,Integer>) request.getAttribute("number");
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
<form name="form1" action="EmployeeManage" method="get">
<input type="hidden" name="token" value=""> 
  <div class="row">
    <div class="col-md-3 pull-left sidebar">
      <div class="panel panel-default panel-actions view-filter-btns">
        <div class="panel-heading">
          <h3 class="panel-title"> <i class="fa fa-filter"></i>&nbsp;考勤 </h3>
        </div>
        <div class="list-group"> 
        <%for(int i = 0; i < positionList.size(); i++) {%>
        	<a class="list-group-item" > &nbsp; <span><%=positionList.get(i).getJob() %></span> &nbsp;<span class="badge"><%=number.get(positionList.get(i).getJob())%></span> </a> 
        <%}
        %>
        </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>雇员管理</h1>
        <ol class="breadcrumb">
          <li> <a> 系统管理 </a> </li>
          <li class="active"> 雇员管理 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <table id="example" class="table table-bordered table-hover">
        <thead>
          <tr >
            <th><input type="checkbox" name="DeleteIDAll" onclick="invertSelectType()" />员工号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>电话</th>
            <th>身份证号</th>
            <th>工资</th>
            <th>职位</th>
          </tr>
        </thead>
        <tbody>
        <%for(int i = 0;  i < employees.size(); i++) {%>
        	<tr>
            <td><input type="checkbox" name="operateID" value="<%=employees.get(i).getSno()%>" /><%=employees.get(i).getSno() %></td>
            <td><%=employees.get(i).getName() %></td>
            <td><%=employees.get(i).getGender()%></td>
            <td><%=employees.get(i).getTel() %></td>
            <td><%=employees.get(i).getID() %></td>
            <td><%=employees.get(i).getSalary()%></td>
            <td><%=positions.get(i).getJob() %></td> 
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
        <a href="javascript:submitAdd()" class="list-group-item">&nbsp;添加</a> 
        <a href="javascript:submitChange()" class="list-group-item">&nbsp;修改 </a>
        <a href="javascript:submitDelete()" class="list-group-item">&nbsp;删除</a> 
        <a class="list-group-item">&nbsp;注销 </a> </div>

    </div>
  </div>
</div>
    </form>
</div>
  </body>
</html>
