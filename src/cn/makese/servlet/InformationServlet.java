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
import cn.makese.dao.PositionDAO;
import cn.makese.dao.RoleDAO;
import cn.makese.model.Employee;
import cn.makese.model.Position;
import cn.makese.model.Role;

public class InformationServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public InformationServlet() {
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
		RoleDAO roleDAO = new RoleDAO();
		ArrayList<Role> roles = roleDAO.findAllRole();
		PositionDAO positionDAO = new PositionDAO();
		ArrayList<Position> positions = positionDAO.findAllPosition();
		request.setAttribute("roles", roles);
		request.setAttribute("positions", positions);
		RequestDispatcher rd = request.getRequestDispatcher("information.jsp");
		rd.forward(request, response);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String sno = request.getParameter("staffno");
		String salary = request.getParameter("salary");
		String ID = request.getParameter("idnumber");
		String tel = request.getParameter("phonenumber");
		Employee employee = new Employee();
		employee.setSno(sno);
		employee.setName(name);
		employee.setGender(gender);
		employee.setID(ID);
		employee.setSalary(Integer.parseInt(salary));
		employee.setTel(tel);
		EmployeeDAO employeeDAO = new EmployeeDAO();
		try {
			employeeDAO.Change(employee);
		} catch (Exception e) {
			System.out.println("修改员工信息错误");
			e.printStackTrace();
		}finally {
			employeeDAO.releaseSource();
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
