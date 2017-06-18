package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.makese.dao.WorkDAO;
import cn.makese.dao.WorkInfoDAO;
import cn.makese.model.Employee;
import cn.makese.model.Work;
import cn.makese.model.WorkInfo;

public class SignInServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SignInServlet() {
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
		Work work = (Work)session.getAttribute("work");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String hours = sdf.format(date);
		sdf = new SimpleDateFormat("yyyy-M-d");
		String time = sdf.format(date);
		WorkDAO workDAO = new WorkDAO();
		WorkInfoDAO workInfoDAO = new WorkInfoDAO();
		WorkInfo workInfo = new WorkInfo();
		
		int hour = Integer.parseInt(hours);
		if(6 < hour && hour < 9 && !workInfoDAO.isWork(employee.getSno()) ) {
			work.setWorkDay(work.getWorkDay() +1 );
			workInfo.setSno(employee.getSno());
			workInfo.setDate(time);
			workInfo.setWork("上班");
			try {
				workDAO.Change(work);
				workInfoDAO.Save(workInfo);
			} catch (Exception e) {
				System.out.println("修改工作信息错误");
				e.printStackTrace();
			} finally {
				workDAO.releaseSource();
				workInfoDAO.releaseSource();
			}
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('签到成功,返回主界面！！！');window.location='main.jsp';</script>");
		} else if (18 < hour && hour < 23 && !workInfoDAO.isOverWork(employee.getSno())) {
			work.setOverDay(work.getOverDay() + 1);
			workInfo.setSno(employee.getSno());
			workInfo.setDate(time);
			workInfo.setWork("加班");
			try {
				workDAO.Change(work);
				workInfoDAO.Save(workInfo);
			} catch (Exception e) {
				System.out.println("修改工作信息错误");
				e.printStackTrace();
			} finally {
				workDAO.releaseSource();
				workInfoDAO.releaseSource();
			}
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('加班签到成功,返回主界面！！！');window.location='main.jsp';</script>");
		} else {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('你今天已经签到了！！！');window.location='main.jsp';</script>");
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
