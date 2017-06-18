package cn.makese.dao;

import java.sql.ResultSet;

import cn.makese.model.Employee_Position;

public class Employee_PositionDAO extends BasicDAO<Employee_Position> {
	public int findPnoBySno(String sno) {
		Employee_Position employee_Position = new Employee_Position();
		employee_Position.setSno(sno);
		ResultSet rs;
		try {
			rs = FindByEqual(employee_Position);
			if(rs.next()) {
				return rs.getInt("Pno");
			}
		} catch (Exception e) {
			System.out.println("≤È’“÷∞Œª¥ÌŒÛ");
			e.printStackTrace();
		}
		finally {
			releaseSource();
		}
		return -1;
		
	}
}
