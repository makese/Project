package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;

import cn.makese.dao.EmployeeDAO;
import cn.makese.dao.Employee_PositionDAO;
import cn.makese.dao.Employee_RoleDAO;
import cn.makese.dao.LeaveDAO;
import cn.makese.dao.PositionDAO;
import cn.makese.dao.RoleDAO;
import cn.makese.dao.UserInfoDAO;
import cn.makese.dao.WorkDAO;
import cn.makese.dao.WorkInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.Employee_Position;
import cn.makese.model.Employee_Role;
import cn.makese.model.Leave;
import cn.makese.model.Position;
import cn.makese.model.Role;
import cn.makese.model.UserInfo;

public class ManageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ManageServlet() {
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
		Role role = (Role) request.getSession().getAttribute("role");
		if(!"普通用户".equals(role.getRole())) {
			String token = request.getParameter("token");
			if ("main".equals(token)) {
				doGetMain(request, response);
			}
			else if("employee".equals(token) && "超级管理员".equals(role.getRole())) {
				doGetEmployee(request, response);
			}
			else if ("userInfo".equals(token) && "超级管理员".equals(role.getRole())) {
				doGetUserInfo(request, response);
			}
			else if ("超级管理员".equals(role.getRole())) {
				doGetLeave(request, response);
			}else {
				response.setCharacterEncoding("gbk");
				PrintWriter out = response.getWriter();
				out.write("<script language='javascript'>alert('权限不足！！！');window.location='ManageServlet?token=main';</script>");
			}
		}
		else {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('权限不足！！！');window.location='main.jsp';</script>");
		}
		
/*		RoleDAO roleDAO = new RoleDAO();
		ArrayList<Role> roles = roleDAO.findAllRole();
		PositionDAO positionDAO = new PositionDAO();
		ArrayList<Position> positions = positionDAO.findAllPosition();
		request.setAttribute("roles", roles);
		request.setAttribute("positions", positions);
		RequestDispatcher rd = request.getRequestDispatcher("addemployee.jsp");
		rd.forward(request, response);*/
		
	}
	
	private void doGetLeave(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
		LeaveDAO leaveDAO = new LeaveDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		ArrayList<Leave> leaves = leaveDAO.findAllLeave();
		HashMap<String, Integer> number = new HashMap<String, Integer>();
		number.put("未审核", 0);
		number.put("允许", 0);
		number.put("拒绝", 0);
		for (Leave leave : leaves) {
			Employee employee = new Employee();
			employee = employeeDAO.findEmployeeBySno(leave.getSno());
			employees.add(employee);
			String state = leave.getState();
			number.put(state, number.get(state) + 1);
		}
		request.setAttribute("employees", employees);
		request.setAttribute("leaves", leaves);
		request.setAttribute("number", number);
		RequestDispatcher rd = request.getRequestDispatcher("/leavemanage.jsp");
		rd.forward(request, response);
		
	}

	public void doGetMain(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(new EmployeeDAO().findAllEmployee().size());
		numbers.add(new UserInfoDAO().findAllUserInfo().size());
		numbers.add(new WorkDAO().findAllEmployeeLeaveDay());
		numbers.add(new LeaveDAO().findLeaveEmployeeNum());
		request.setAttribute("numbers", numbers);
		RequestDispatcher rd = request.getRequestDispatcher("/managemain.jsp");
		rd.forward(request, response);
	}
	
	public void doGetEmployee(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		PositionDAO positionDAO = new PositionDAO();
		Employee_PositionDAO employee_PositionDAO = new Employee_PositionDAO();
		ArrayList<Employee> employees = employeeDAO.findAllEmployee();
		ArrayList<Position> positionList = positionDAO.findAllPosition();
		ArrayList<Position> positions = new ArrayList<Position>();
		HashMap<String, Integer> number = new HashMap<String, Integer>();
		for (Position position : positionList) {
			number.put(position.getJob(), 0);
		}
		
		for (Employee employee : employees) {
			Position position = new Position();
			position = positionDAO.findPositionBySno(employee.getSno());
			positions.add(position);
			String job = position.getJob();
			number.put(job, number.get(job)+1);
		}
		request.setAttribute("employees", employees);
		request.setAttribute("positions", positions);
		request.setAttribute("positionlist", positionList);
		request.setAttribute("number", number);
		RequestDispatcher rd = request.getRequestDispatcher("/employeemanage.jsp");
		rd.forward(request, response);
	}
	
	public void doGetUserInfo(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		RoleDAO roleDAO = new RoleDAO();
		Employee_RoleDAO employee_RoleDAO = new Employee_RoleDAO();
		ArrayList<UserInfo> userInfos = userInfoDAO.findAllUserInfo();
		ArrayList<Role> roleList = roleDAO.findAllRole();
		ArrayList<Role> roles = new ArrayList<Role>();
		HashMap<String, Integer> number = new HashMap<String , Integer>();
		for (Role role : roleList) {
			number.put(role.getRole(),0);
		}
		for (UserInfo userInfo : userInfos) {
			Role role = new Role();
			role = roleDAO.findRolebyRoleID(employee_RoleDAO.findRoleIDBySno(userInfo.getUserID()));
			roles.add(role);
			String roleName = role.getRole();
			number.put(roleName, number.get(roleName) + 1);
		}
		request.setAttribute("userinfos", userInfos);
		request.setAttribute("rolelist", roleList);
		request.setAttribute("roles", roles);
		request.setAttribute("number", number);
		RequestDispatcher rd = request.getRequestDispatcher("/userinfomanage.jsp");
		rd.forward(request, response);
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

		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		String gender = request.getParameter("gender");
		gender = new String(gender.getBytes("ISO-8859-1"),"utf-8");
		String sno = request.getParameter("staffno");
		sno = new String(sno.getBytes("ISO-8859-1"),"utf-8");
		String salary = request.getParameter("salary");
		salary = new String(salary.getBytes("ISO-8859-1"),"utf-8");
		String ID = request.getParameter("idnumber");
		ID = new String(ID.getBytes("ISO-8859-1"),"utf-8");
		String tel = request.getParameter("phonenumber");
		tel = new String(tel.getBytes("ISO-8859-1"),"utf-8");
		String position = request.getParameter("position");
		String role = request.getParameter("role");
		Employee employee = new Employee();
		employee.setSno(sno);
		employee.setName(name);
		employee.setGender(gender);
		employee.setID(ID);
		employee.setSalary(Integer.parseInt(salary));
		employee.setTel(tel);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee_Position employee_Position = new Employee_Position();
		employee_Position.setSno(sno);
		employee_Position.setPno(Integer.parseInt(position));
		Employee_PositionDAO employee_PositionDAO = new Employee_PositionDAO();
		Employee_Role employee_Role = new Employee_Role();
		Employee_RoleDAO employee_RoleDAO = new Employee_RoleDAO();
		employee_Role.setSno(sno);
		employee_Role.setRoleID(Integer.parseInt(role));
		UserInfo userInfo = new UserInfo();
		userInfo.setUserID(sno);
		userInfo.setUserPsw(sno);
		UserInfoDAO userInfoDAO = new UserInfoDAO();
		try {
			employeeDAO.Save(employee);
			userInfoDAO.Save(userInfo);
			employee_PositionDAO.Save(employee_Position);
			employee_RoleDAO.Save(employee_Role);
		} catch (Exception e) {
			System.out.println("添加员工错误");
			e.printStackTrace();
		} finally {
			employeeDAO.releaseSource();
			userInfoDAO.releaseSource();
			employee_PositionDAO.releaseSource();
			employee_RoleDAO.releaseSource();
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
