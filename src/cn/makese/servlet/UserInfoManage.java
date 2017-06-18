package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.makese.dao.EmployeeDAO;
import cn.makese.dao.Employee_PositionDAO;
import cn.makese.dao.Employee_RoleDAO;
import cn.makese.dao.PositionDAO;
import cn.makese.dao.RoleDAO;
import cn.makese.dao.UserInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.Employee_Role;
import cn.makese.model.Position;
import cn.makese.model.Role;
import cn.makese.model.UserInfo;

public class UserInfoManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserInfoManage() {
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
		String[] operateID = request.getParameterValues("operateID");
		if(operateID == null ) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要修改的数据！！！');window.location='ManageServlet?token=userInfo';</script>");
		}
		else if(operateID.length > 1) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('修改数据只能选中一条数据！！！');window.location='ManageServlet?token=userInfo';</script>");
		}
		else {
			UserInfoDAO userInfoDAO = new UserInfoDAO();
			UserInfo userInfo = userInfoDAO.findUserInfoBySno(operateID[0]);
			int roleID = new Employee_RoleDAO().findRoleIDBySno(operateID[0]);
			Role role = new RoleDAO().findRolebyRoleID(roleID);
			ArrayList<Role> roles = new RoleDAO().findAllRole();
			request.setAttribute("userInfo", userInfo);
			request.setAttribute("role", role);
			request.setAttribute("roles", roles);
			RequestDispatcher rd = request.getRequestDispatcher("changeuserinfo.jsp");
			rd.forward(request, response);
		}
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
			String userID = (String)request.getParameter("userid");
			String userPWD = (String)request.getParameter("password");
			String roleID = (String)request.getParameter("role");
			UserInfo userInfo = new UserInfo();
			userInfo.setUserID(userID);
			userInfo.setUserPsw(userPWD);
			Employee_Role employee_Role = new Employee_Role();
			employee_Role.setSno(userID);
			employee_Role.setRoleID(Integer.parseInt(roleID));
			UserInfoDAO userInfoDAO = new UserInfoDAO();
			Employee_RoleDAO employee_RoleDAO = new Employee_RoleDAO();
			try {
				userInfoDAO.Change(userInfo);
				employee_RoleDAO.Change(employee_Role);
			} catch (Exception e) {
				System.out.println("修改用户错误");
				e.printStackTrace();
			}
			finally {
				userInfoDAO.releaseSource();
				employee_RoleDAO.releaseSource();
			}
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('修改成功！！！');window.location='ManageServlet?token=userInfo';</script>");
			
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
