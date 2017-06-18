package cn.makese.dao;

import java.sql.ResultSet;

import cn.makese.model.Employee_Role;

public class Employee_RoleDAO extends BasicDAO<Employee_Role> {
	public int findRoleIDBySno(String sno) {
		Employee_Role employee_Role = new Employee_Role();
		employee_Role.setSno(sno);
		ResultSet rs;
		try {
			rs = FindByEqual(employee_Role);
			if(rs.next()) {
				return rs.getInt("roleid");
			}
		} catch (Exception e) {
			System.out.println("查找权限出错");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return -1;
		
	}
}
