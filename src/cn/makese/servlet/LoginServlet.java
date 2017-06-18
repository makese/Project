package cn.makese.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.makese.dao.EmployeeDAO;
import cn.makese.dao.Employee_PositionDAO;
import cn.makese.dao.Employee_RoleDAO;
import cn.makese.dao.LeaveDAO;
import cn.makese.dao.PositionDAO;
import cn.makese.dao.RoleDAO;
import cn.makese.dao.UserInfoDAO;
import cn.makese.dao.WorkDAO;
import cn.makese.dbmanager.ConnectionPool;
import cn.makese.model.Employee;
import cn.makese.model.Leave;
import cn.makese.model.Position;
import cn.makese.model.Role;
import cn.makese.model.UserInfo;
import cn.makese.model.Work;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		connectionPool.closePool();
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
		String code = (String)session.getAttribute("code");
		String inputcode =(String)request.getParameter("code");
		if(code.equals(inputcode)) {
			UserInfo user = new UserInfo();
			user.setUserID(request.getParameter("username"));
			user.setUserPsw(request.getParameter("password"));
			UserInfoDAO userDAO = new UserInfoDAO();
			boolean b =false;
			try {
				b = userDAO.LoginUser(user);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("µÇÂ¼´íÎó");
			} finally {
				userDAO.releaseSource();
			}
			if(b==true) {
				System.out.println("µÇÂ¼³É¹¦");
				Employee employee = new EmployeeDAO().findEmployeeBySno(user.getUserID());
				session.setAttribute("employee", employee);
				Role role = new RoleDAO().findRolebySno(employee.getSno());
				session.setAttribute("role", role);
				Position position = new PositionDAO().findPositionBySno(employee.getSno());
				session.setAttribute("position", position);
				Work work = new WorkDAO().findWorkBySno(employee.getSno());
				session.setAttribute("work", work);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH");
				String hours = sdf.format(date);
				int hour = Integer.parseInt(hours);
				Integer signIn = 0;
				if((hour < 9 && hour > 7)||( 18 < hour && hour < 23)){
					signIn = 1;
				}
				session.setAttribute("signin", signIn);
				ArrayList<Leave> leaves = new LeaveDAO().findLeaveBySno(employee.getSno());
				session.setAttribute("leaves", leaves);
				response.sendRedirect(request.getContextPath()+"/main.jsp");
			}
			else {
				
				response.sendRedirect(request.getContextPath()+"/login.jsp?message=1");
			}
		}
		else {
			
			response.sendRedirect(request.getContextPath()+"/login.jsp?message=2");
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
