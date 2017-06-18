package cn.makese.dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sun.java2d.pipe.SpanShapeRenderer.Simple;

import cn.makese.dbmanager.DataAccessFactory;
import cn.makese.dbmanager.IDataAccess;
import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Leave;

public class LeaveDAO extends BasicDAO<Leave> {
	public int findLeaveEmployeeNum() {
		int num = 0;
		Leave leave = new Leave();
		leave.setState("Î´ÉóºË");
		ResultSet rs;
		MyTableConvert myTableConvert = new MyTableConvert();
		try {
			rs = FindByEqual(leave);
			ArrayList<Leave> leaves = (ArrayList<Leave>) myTableConvert.convertToList(rs, new Leave().getClass());
			num = leaves.size();
		} catch (Exception e) {
			System.out.println("²éÕÒËùÓĞÎ´ÉóºËÇë¼ÙÇëÇó´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return num;
	}
	public ArrayList<Leave> findAllLeave() {
		ArrayList<Leave> leaves = new ArrayList<Leave>();
		MyTableConvert myTableConvert = new MyTableConvert();
		ResultSet rs;
		try {
			rs = FindAll(new Leave());
			leaves = (ArrayList<Leave>) myTableConvert.convertToList(rs, new Leave().getClass());
		} catch (Exception e) {
			System.out.println("²éÕÒËùÓĞÇë¼ÙÇëÇó´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return leaves;
		
	}
	public void changeLeaveState(String lno,String state) {
		LeaveDAO leaveDAO = new LeaveDAO();
		Leave leave = new Leave();
		leave.setLno(lno);
		leave.setState(state);
		try {
			leaveDAO.Change(leave);
		} catch (Exception e) {
			System.out.println("ĞŞ¸ÄÇë¼ÙÇëÇó×´Ì¬´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
	}
	public void DeleteLeave(String lno) {
		LeaveDAO leaveDAO = new LeaveDAO();
		Leave leave = new Leave();
		leave.setLno(lno);
		try {
			leaveDAO.Delete(leave);
		} catch (Exception e) {
			System.out.println("É¾³ıÇë¼ÙÇëÇó´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
	}
	public void deleteAll() {
		String sql = "delete from leave";
		IDataAccess dataAccess;
		try {
			dataAccess = DataAccessFactory.CreateDataAccess(databaseType);
			dataAccess.executeUpdate(sql, new ArrayList<Object>());
		} catch (Exception e) {
			System.out.println("É¾³ıËùÓĞÇë¼Ù¼ÇÂ¼´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
	}
	public ArrayList<Leave> findLeaveBySno(String sno) {
		Leave leave = new Leave();
		MyTableConvert myTableConvert = new MyTableConvert();
		leave.setSno(sno);
		ArrayList<Leave> leaves = new ArrayList<Leave>();
		ResultSet rs;
		try {
			rs = FindByEqual(leave);
			leaves = (ArrayList<Leave>) myTableConvert.convertToList(rs, new Leave().getClass());
		} catch (Exception e) {
			System.out.println("²éÕÒËùÓĞÇë¼ÙÇëÇó´íÎó");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return leaves;
	}
}
