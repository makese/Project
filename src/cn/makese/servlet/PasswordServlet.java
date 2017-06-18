package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.makese.dao.UserInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.UserInfo;

public class PasswordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PasswordServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		UserInfo userInfo = userInfoDAO.findUserInfoBySno(employee.getSno());
		String inputExistingPassword = request.getParameter("existingpw");
		if(userInfo.getUserPsw().equals(inputExistingPassword)) {
			System.out.println("旧密码输入正确");
			String inputNewPassword = request.getParameter("newpw");
			userInfo.setUserPsw(inputNewPassword);
			try {
				userInfoDAO.Change(userInfo);
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.write("<script language='javascript'>alert('密码修改成功数据！！！');window.location='login.jsp';</script>");
			} catch (Exception e) {
				System.out.println("修改用户密码错误");
				e.printStackTrace();
			} finally {
				userInfoDAO.releaseSource();
			}
		}
		else {
			System.out.println("旧密码输入错误");
			response.sendRedirect(request.getContextPath()+"/password.jsp?message=2");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
