package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {

	public LogoutServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('注销成功,返回登录界面！！！');window.location='login.jsp';</script>");
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
