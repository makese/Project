package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.crypto.provider.RSACipher;

import cn.makese.dbmanager.DataAccessFactory;
import cn.makese.dbmanager.IDataAccess;
import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Work;

public class WorkDAO extends BasicDAO<Work> {
	public Work findWorkBySno(String sno) {
		Work work = new Work();
		work.setSno(sno);
		ResultSet rs;
		try {
			rs = FindByEqual(work);
			if(rs.next()) {
				work.setWorkDay(rs.getInt("workday"));
				work.setOverDay(rs.getInt("overday"));
				work.setLackDay(rs.getInt("lackday"));
				work.setAbsence(rs.getInt("absence"));
			}
		} catch (Exception e) {
			System.out.println("考勤信息获取错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return work;
	}
	public int findAllEmployeeLeaveDay() {
		String sql = "select count(sno) as num from work where lackday != 0";
		IDataAccess dataAccess;
		ResultSet rs;
		int num = 0;
		try {
			dataAccess = DataAccessFactory.CreateDataAccess(databaseType);
			rs = dataAccess.getResultSet(sql, new ArrayList<Object>());
			if(rs.next());
			num = rs.getInt("num");
		} catch (Exception e) {
			System.out.println("获取缺勤人数获取错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return num;
		
	}
	public ArrayList<Work> findAllWork() {
		ArrayList<Work> works = new ArrayList<Work>();
		ResultSet rs;
		MyTableConvert myTableConvert = new MyTableConvert();
		try {
			rs = FindAll(new Work());
			works = (ArrayList<Work>) myTableConvert.convertToList(rs,new Work().getClass());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return works;
	}
}
