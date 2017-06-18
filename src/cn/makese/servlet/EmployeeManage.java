package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import cn.makese.dao.WorkDAO;
import cn.makese.model.Employee;
import cn.makese.model.Employee_Position;
import cn.makese.model.Employee_Role;
import cn.makese.model.Position;
import cn.makese.model.Role;
import cn.makese.model.UserInfo;
import cn.makese.model.Work;

public class EmployeeManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public EmployeeManage() {
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
		String token = request.getParameter("token");
		token = new String(token.getBytes("ISO-8859-1"),"utf-8");
		if("修改".equals(token)) {
			doGetChange(request, response);
		}
		else if ("删除".equals(token)) {
			doGetDelete(request, response);
		} else {
			doGetAdd(request,response);
		}
	}
	
	private void doGetAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RoleDAO roleDAO = new RoleDAO();
		ArrayList<Role> roles = roleDAO.findAllRole();
		PositionDAO positionDAO = new PositionDAO();
		ArrayList<Position> positions = positionDAO.findAllPosition();
		request.setAttribute("roles", roles);
		request.setAttribute("positions", positions);
		RequestDispatcher rd = request.getRequestDispatcher("addemployee.jsp");
		rd.forward(request, response);
		
	}

	public void doGetChange(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String[] operateID = request.getParameterValues("operateID");
		if(operateID == null ) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要修改的数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		else if(operateID.length > 1) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('修改数据只能选中一条数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		else {
			EmployeeDAO employeeDAO = new EmployeeDAO();
			Employee employee = employeeDAO.findEmployeeBySno(operateID[0]);
			PositionDAO positionDAO = new PositionDAO();
			ArrayList<Position> positions = positionDAO.findAllPosition();
			Position position = new PositionDAO().findPositionBySno(operateID[0]);
			request.setAttribute("employee", employee);
			request.setAttribute("position", position);
			request.setAttribute("positions", positions);
			RequestDispatcher rd = request.getRequestDispatcher("changeemployee.jsp");
			rd.forward(request, response);
		}
	}
	
	public void doGetDelete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String[] operateID = request.getParameterValues("operateID");
		if(operateID == null) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要删除的数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		else {
			for (String string : operateID) {
				Employee employee = new Employee();
				employee.setSno(string);
				EmployeeDAO employeeDAO = new EmployeeDAO();
				try {
					employeeDAO.Delete(employee);
				} catch (Exception e) {
					System.out.println("删除雇员出错");
					e.printStackTrace();
				} finally {
					employeeDAO.releaseSource();
				}
			}
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('删除数据成功！！！');window.location='ManageServlet?token=employee';</script>");
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
		String token = request.getParameter("token");
		token = new String(token.getBytes("ISO-8859-1"),"utf-8");
		if("添加".equals(token)) {
			doPostAdd(request, response);
		}
		else if ("修改".equals(token)) {
			doPostChange(request, response);
		}	
		
	}

	private void doPostAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		WorkDAO workDAO = new WorkDAO();
		Work work = new Work();
		work.setSno(employee.getSno());
		work.setWorkDay(0);
		work.setAbsence(0);
		work.setLackDay(0);
		work.setOverDay(0);
		try {
			employeeDAO.Save(employee);
			userInfoDAO.Save(userInfo);
			employee_PositionDAO.Save(employee_Position);
			employee_RoleDAO.Save(employee_Role);
			workDAO.Save(work);
		} catch (Exception e) {
			System.out.println("添加员工错误");
			e.printStackTrace();
		} finally {
			employeeDAO.releaseSource();
			userInfoDAO.releaseSource();
			employee_PositionDAO.releaseSource();
			employee_RoleDAO.releaseSource();
			workDAO.releaseSource();
		}
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('添加成功！！！');window.location='ManageServlet?token=employee';</script>");
	}

	private void doPostChange(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("ISO-8859-1"),"utf-8");
		String gender = request.getParameter("gender");
		gender = new String(gender.getBytes("ISO-8859-1"),"utf-8");
		String sno = request.getParameter("sno");
		sno = new String(sno.getBytes("ISO-8859-1"),"utf-8");
		String salary = request.getParameter("salary");
		salary = new String(salary.getBytes("ISO-8859-1"),"utf-8");
		String ID = request.getParameter("idnumber");
		ID = new String(ID.getBytes("ISO-8859-1"),"utf-8");
		String tel = request.getParameter("phonenumber");
		tel = new String(tel.getBytes("ISO-8859-1"),"utf-8");
		String position = request.getParameter("position");
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
		try {
			employeeDAO.Change(employee);
			employee_PositionDAO.Change(employee_Position);
		} catch (Exception e) {
			System.out.println("修改员工错误");
			e.printStackTrace();
		}	
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('修改成功！！！');window.location='ManageServlet?token=employee';</script>");
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
