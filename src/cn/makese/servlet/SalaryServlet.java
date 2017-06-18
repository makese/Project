package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.swing.internal.plaf.metal.resources.metal;

import cn.makese.dao.EmployeeDAO;
import cn.makese.dao.LeaveDAO;
import cn.makese.dao.PositionDAO;
import cn.makese.dao.TaxDAO;
import cn.makese.dao.WorkDAO;
import cn.makese.dao.WorkInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.Position;
import cn.makese.model.Work;

public class SalaryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalaryServlet() {
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
		WorkDAO workDAO = new WorkDAO();
		TaxDAO taxDAO = new TaxDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		PositionDAO positionDAO = new PositionDAO();
		ArrayList<Work> works = workDAO.findAllWork();
		for (Work work : works) {
			Employee employee = employeeDAO.findEmployeeBySno(work.getSno());
			Position position = positionDAO.findPositionBySno(work.getSno());
			int salary = (int) (position.getEsalary() * (work.getWorkDay() + work.getOverDay() * 3 + work.getAbsence() * 0.5)) + position.getBonus();
			salary = taxDAO.netSalary(salary);
			employee.setSalary(salary);
			try {
				
				employeeDAO.Change(employee);
				work.setWorkDay(0);
				work.setAbsence(0);
				work.setLackDay(0);
				work.setOverDay(0);
				workDAO.Change(work);
			} catch (Exception e) {
				System.out.println("计算工资错误");
				e.printStackTrace();
			} finally {
				employeeDAO.releaseSource();
				workDAO.releaseSource();
			}
		}
		LeaveDAO leaveDAO = new LeaveDAO();
		leaveDAO.deleteAll();
		WorkInfoDAO workInfoDAO = new WorkInfoDAO();
		workInfoDAO.deleteAll();
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('结算工资成功！！！');window.location='ManageServlet?token=main';</script>");
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
