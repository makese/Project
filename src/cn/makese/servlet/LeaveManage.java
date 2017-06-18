package cn.makese.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.makese.dao.LeaveDAO;
import cn.makese.dao.WorkDAO;
import cn.makese.dao.WorkInfoDAO;
import cn.makese.model.Work;
import cn.makese.model.WorkInfo;

public class LeaveManage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LeaveManage() {
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
		String token = request.getParameter("token");
		token = new String(token.getBytes("ISO-8859-1"),"utf-8");
		if("允许".equals(token)) {
			doPostPermit(request, response);
		}
		else if ("拒绝".equals(token)) {
			doPostDecline(request, response);
		}
		else {
			doPostDelete(request,response);
		} 
	}

	private void doPostPermit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] operateID = request.getParameterValues("operateID");
		if(operateID == null ) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要允许的数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		LeaveDAO leaveDAO = new LeaveDAO();
		WorkDAO workDAO = new WorkDAO();
		Work work = new Work();
		for (String string : operateID) {
			String[] temp = string.split("-");
			work = workDAO.findWorkBySno(temp[0]);
			int day = Integer.parseInt(temp[4]);
			int startDay = Integer.parseInt(temp[3]);
			work.setAbsence(work.getAbsence() + day);
			for(int i = startDay; i < startDay + day; i++) {
				String date = temp[1] + "-" + temp[2] + "-" + i;
				WorkInfo workInfo = new WorkInfo();
				WorkInfoDAO workInfoDAO = new WorkInfoDAO();
				workInfo.setSno(temp[0]);
				workInfo.setWork("请假");
				workInfo.setDate(date);
				try {
					workInfoDAO.Save(workInfo);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					workInfoDAO.releaseSource();
				}
			}
			try {
				workDAO.Change(work);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				workDAO.releaseSource();
			}
			leaveDAO.changeLeaveState(string, "允许");
			
		}
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('允许成功！！！');window.location='ManageServlet?token=leave';</script>");
	}

	private void doPostDecline(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] operateID = request.getParameterValues("operateID");
		if(operateID == null) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要拒绝的数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		LeaveDAO leaveDAO = new LeaveDAO();
		for (String string : operateID) {
			leaveDAO.changeLeaveState(string, "拒绝");
		}
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('拒绝成功！！！');window.location='ManageServlet?token=leave';</script>");
		
	}

	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] operateID = request.getParameterValues("operateID");
		if(operateID.length == 0 ) {
			response.setCharacterEncoding("gbk");
			PrintWriter out = response.getWriter();
			out.write("<script language='javascript'>alert('请选中需要删除的数据！！！');window.location='ManageServlet?token=employee';</script>");
		}
		LeaveDAO leaveDAO = new LeaveDAO();
		for (String string : operateID) {
			leaveDAO.DeleteLeave(string);
		}
		response.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		out.write("<script language='javascript'>alert('删除成功！！！');window.location='ManageServlet?token=leave';</script>");
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
