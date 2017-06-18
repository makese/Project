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
import javax.servlet.http.HttpSession;

import cn.makese.dao.LeaveDAO;
import cn.makese.model.Employee;
import cn.makese.model.Leave;
import cn.makese.model.Position;
import cn.makese.model.Work;

public class LeaveServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeaveServlet() {
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
		HttpSession session = request.getSession();
		Employee employee = (Employee)session.getAttribute("employee");
		LeaveDAO leaveDAO = new LeaveDAO();
		ArrayList<Leave> leaves = leaveDAO.findLeaveBySno(employee.getSno());
		HashMap<String, Integer> number = new HashMap<String, Integer>();
		number.put("Î´ÉóºË", 0);
		number.put("ÔÊÐí", 0);
		number.put("¾Ü¾ø", 0);
		for (Leave leave : leaves) {
			String state = leave.getState();
			number.put(state, number.get(state) + 1);
		}
		request.setAttribute("employee", employee);
		request.setAttribute("leaves", leaves);
		request.setAttribute("number", number);
		RequestDispatcher rd = request.getRequestDispatcher("/leave.jsp");
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
		HttpSession session = request.getSession();
		String date = (String) request.getParameter("date");
		String day = (String) request.getParameter("day");
		int dayInt = Integer.parseInt(day);
		String reason = (String) request.getParameter("reason");
		reason = new String(reason.getBytes("ISO-8859-1"),"utf-8");
		LeaveDAO leaveDAO = new LeaveDAO();
		Leave leave = new Leave();
		Employee employee = (Employee) session.getAttribute("employee");
		Work work = (Work) session.getAttribute("work");
		Position position = (Position) session.getAttribute("position");
		if(position.getLeaveday() - work.getAbsence()- dayInt > 0) {
			leave.setDate(date);
			leave.setSno(employee.getSno());
			leave.setDay(dayInt);
			leave.setReason(reason);
			leave.setState("Î´ÉóºË");
			leave.setLno(employee.getSno() + "-" + date + "-" + dayInt);
			try {
				leaveDAO.Save(leave);
			} catch (Exception e) {
				System.out.println("±£´æÇë¼ÙÐÅÏ¢³ö´í");
				e.printStackTrace();
			} finally {
				leaveDAO.releaseSource();
			}
		}
		else {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('ÄúµÄÇë¼ÙÌìÊý³¬³öÊ£ÓàÇë¼ÙÌìÊý£¡£¡£¡');window.location='main.jsp';</script>");
		}
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('Çë¼Ù³É¹¦£¡£¡£¡');window.location='main.jsp';</script>");
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
