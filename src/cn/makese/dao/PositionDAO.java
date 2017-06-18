package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import cn.makese.dbmanager.DataAccessFactory;
import cn.makese.dbmanager.IDataAccess;
import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Position;

public class PositionDAO extends BasicDAO<Position> {
	public Position findPositionByPno(int pno) {
		Position position = new Position();
		position.setPno(pno);
		ResultSet rs;
		try {
			rs = FindByEqual(position);
			if(rs.next()) {
				position.setBonus(rs.getInt("Bonus"));
				position.setEsalary(rs.getInt("esalary"));
				position.setJob(rs.getString("job"));
				position.setLeaveday(rs.getInt("leaveday"));
			}
		} catch (Exception e) {
			System.out.println("获取职位信息出错");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		
		return position;
	}
	public ArrayList<Position> findAllPosition() {
		ArrayList<Position> positions = null;
		ResultSet rs = null;
		MyTableConvert myTableConvert = new MyTableConvert();
		try {
			rs = FindAll(new Position());
			positions = (ArrayList<Position>) myTableConvert.convertToList(rs,new Position().getClass());
		} catch (Exception e) {
			System.out.println("查找所有职位错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return positions;
		
	}
	public Position findPositionBySno(String sno) {
		Position position = new Position();
		String sql = "SELECT position.* FROM employee INNER JOIN employee_position ON employee.Sno = employee_position.Sno INNER JOIN position ON employee_position.Pno = position.Pno where employee.Sno = '" + sno + "'";
		IDataAccess dataAccess;
		ResultSet rs;
		try {
			dataAccess = DataAccessFactory.CreateDataAccess(databaseType);
			rs = dataAccess.getResultSet(sql, new ArrayList<Object>());
			if(rs.next()) {
				position.setPno(rs.getInt("pno"));
				position.setJob(rs.getString("job"));
				position.setBonus(rs.getInt("bonus"));
				position.setEsalary(rs.getInt("esalary"));
				position.setLeaveday(rs.getInt("leaveday"));
			}
		} catch (Exception e) {
			System.out.println("获取职位错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return position;
	}
}
