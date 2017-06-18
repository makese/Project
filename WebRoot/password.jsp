<%@ page language="java" import="java.util.*,cn.makese.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'password.jsp' starting page</title>
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
	String message = request.getParameter("message");
	if(message != null) {
		if("1".equals(message)) {%>
		<script type="text/javascript">
     		alert("修改密码成功！！！");
     	</script>
		<%}
		else {%>
		<script type="text/javascript">
     		alert("旧密码输入错误！！！");
     	</script>
	<%}
	}
	String existingpw =(String)request.getAttribute("existingpw");
	%>
<%Employee employee = (Employee)session.getAttribute("employee");
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
      <div class="panel panel-default panel-actions">
        <div class="panel-heading">
          <h3 class="panel-title">&nbsp; 我的帐号</h3>
        </div>
        <div class="list-group"> <a href="<%=basePath %>password.jsp" class="list-group-item active"> 修改密码 </a> <a menuitemname="Change Password" href="clientarea.php?action=changepw" class="list-group-item" id="Primary_Sidebar-My_Account-Change_Password"> 更改密码 </a> </div>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <div class="header-lined">
        <h1>修改密码</h1>
        <ol class="breadcrumb">
          <li> <a> 员工中心 </a> </li>
          <li class="active"> 修改密码 </li>
        </ol>
      </div>
    </div>
    <div class="col-md-9 pull-right">
      <form class="form-horizontal using-password-strength" method="post" action="PasswordServlet" role="form">
        <div class="form-group">
          <label for="inputExistingPassword" class="col-sm-5 control-label">旧密码</label>
          <div class="col-sm-6">
          	<%
          		if(existingpw == null) {%>
          		 	<input type="password" class="form-control" value="" name="existingpw" id="inputExistingPassword" autocomplete="off">
          		<%}
          		else { %>
          			<input type="password" class="form-control" value="<%=existingpw%>" name="existingpw" id="inputExistingPassword" autocomplete="off">
          		<%}
          	 %>
          </div>
        </div>
        <div id="newPassword1" class="form-group has-feedback">
          <label for="inputNewPassword1" class="col-sm-5 control-label">新密码</label>
          <div class="col-sm-6">
            <input type="password" class="form-control" name="newpw" id="inputNewPassword1" autocomplete="off">
            <span class="form-control-feedback glyphicon"></span> <br>
            <div class="progress" id="passwordStrengthBar">
              <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"> <span class="sr-only">New Password Rating: 0%</span> </div>
            </div>
            <div class="alert alert-info"> <strong>技巧：良好的密码</strong><br>
              同时使用大小写字母<br>
              至少包括一个符号 (# $ ! % &amp; 等...)<br>
              不要使用字典单词 </div>
            <script type="text/javascript">
jQuery("#inputNewPassword1").keyup(function() {
    var $newPassword1 = jQuery("#newPassword1");
    var pw = jQuery("#inputNewPassword1").val();
    var pwlength=(pw.length);
    if(pwlength>5)pwlength=5;
    else if(pwlength>4)pwlength=4.5;
    else if(pwlength>2)pwlength=3.5;
    else if(pwlength>0)pwlength=2.5;
    var numnumeric=pw.replace(/[0-9]/g,"");
    var numeric=(pw.length-numnumeric.length);
    if(numeric>3)numeric=3;
    var symbols=pw.replace(/\W/g,"");
    var numsymbols=(pw.length-symbols.length);
    if(numsymbols>3)numsymbols=3;
    var numupper=pw.replace(/[A-Z]/g,"");
    var upper=(pw.length-numupper.length);
    if(upper>3)upper=3;
    var pwstrength=((pwlength*10)-20)+(numeric*10)+(numsymbols*15)+(upper*10);
    if (pwstrength < 0) pwstrength = 0;
    if (pwstrength > 100) pwstrength = 100;

    $newPassword1.removeClass('has-error has-warning has-success');
    jQuery("#inputNewPassword1").next('.form-control-feedback').removeClass('glyphicon-remove glyphicon-warning-sign glyphicon-ok');
    jQuery("#passwordStrengthBar .progress-bar").removeClass("progress-bar-danger progress-bar-warning progress-bar-success").css("width", pwstrength + "%").attr('aria-valuenow', pwstrength);
    jQuery("#passwordStrengthBar .progress-bar .sr-only").html('New Password Rating: ' + pwstrength + '%');
    if (pwstrength < 30) {
        $newPassword1.addClass('has-error');
        jQuery("#inputNewPassword1").next('.form-control-feedback').addClass('glyphicon-remove');
        jQuery("#passwordStrengthBar .progress-bar").addClass("progress-bar-danger");
    } else if (pwstrength < 75) {
        $newPassword1.addClass('has-warning');
        jQuery("#inputNewPassword1").next('.form-control-feedback').addClass('glyphicon-warning-sign');
        jQuery("#passwordStrengthBar .progress-bar").addClass("progress-bar-warning");
    } else {
        $newPassword1.addClass('has-success');
        jQuery("#inputNewPassword1").next('.form-control-feedback').addClass('glyphicon-ok');
        jQuery("#passwordStrengthBar .progress-bar").addClass("progress-bar-success");
    }
    validatePassword2();
});

function validatePassword2() {
    var password1 = jQuery("#inputNewPassword1").val();
    var password2 = jQuery("#inputNewPassword2").val();
    var $newPassword2 = jQuery("#newPassword2");

    if (password2 && password1 !== password2) {
        $newPassword2.removeClass('has-success')
            .addClass('has-error');
        jQuery("#inputNewPassword2").next('.form-control-feedback').removeClass('glyphicon-ok').addClass('glyphicon-remove');
        jQuery("#inputNewPassword2Msg").html('<p class="help-block">两次输入密码不一致</p>');
        jQuery('input[type="submit"]').attr('disabled', 'disabled');    } else {
        if (password2) {
            $newPassword2.removeClass('has-error')
                .addClass('has-success');
            jQuery("#inputNewPassword2").next('.form-control-feedback').removeClass('glyphicon-remove').addClass('glyphicon-ok');
            jQuery('input[type="submit"]').removeAttr('disabled');        } else {
            $newPassword2.removeClass('has-error has-success');
            jQuery("#inputNewPassword2").next('.form-control-feedback').removeClass('glyphicon-remove glyphicon-ok');
        }
        jQuery("#inputNewPassword2Msg").html('');
    }
}

jQuery(document).ready(function(){
    jQuery('.using-password-strength input[type="submit"]').attr('disabled', 'disabled');    jQuery("#inputNewPassword2").keyup(function() {
        validatePassword2();
    });
});

</script>
          </div>
        </div>
        <div id="newPassword2" class="form-group">
          <label for="inputNewPassword2" class="col-sm-5 control-label">确认新密码</label>
          <div class="col-sm-6">
            <input type="password" class="form-control" name="confirmpw" id="inputNewPassword2" autocomplete="off">
            <span class="form-control-feedback glyphicon"></span>          </div>
        </div>
        <div class="form-group">
          <div class="text-center">
            <input class="btn btn-primary" type="submit" value="保存更改" disabled="disabled">
            <input class="btn btn-default" type="reset" value="取消">
          </div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>
