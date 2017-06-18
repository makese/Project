package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.makese.dao.EmployeeDAO;
import cn.makese.dao.UserInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.UserInfo;

public class ResetServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ResetServlet() {
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
		String ID = request.getParameter("ID");
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.findEmpoyeeByID(ID);
		if(employee.getSno()!=null) {
			UserInfo userInfo = new UserInfo();
			UserInfoDAO userInfoDAO = new UserInfoDAO();
			userInfo = userInfoDAO.findUserInfoBySno(employee.getSno());
			request.setAttribute("existingpw", userInfo.getUserPsw());
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			RequestDispatcher rd = request.getRequestDispatcher("password.jsp");
			rd.forward(request, response);
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
