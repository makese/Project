package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Role;

public class RoleDAO extends BasicDAO<Role> {
	public Role findRolebyRoleID(int roleID) {
		Role role = new Role();
		role.setRoleID(roleID);
		ResultSet rs;
		try {
			rs = FindByEqual(role);
			if(rs.next()) {
				role.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			System.out.println("查找职位信息错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		
		return role;
	}
	public ArrayList<Role> findAllRole() {
		ArrayList<Role> roles = null;
		MyTableConvert myTableConvert = new MyTableConvert();
		ResultSet rs = null;
		try {
			rs = FindAll(new Role());
			roles = (ArrayList<Role>) myTableConvert.convertToList(rs,new Role().getClass());
		} catch (Exception e) {
			System.out.println("查找所有权限错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return roles;
	}
	
	public Role findRolebySno(String sno) {
		String sql = "SELECT  role.*  FROM  employee INNER JOIN employee_role ON dbo.employee.Sno = employee_role.Sno INNER JOIN role ON employee_role.RoleID = role.RoleID where employee_role.Sno ='" + sno + "' ";
		ResultSet rs = null;
		Role role = new Role();
		try {
			rs = dataAccess.getResultSet(sql, new ArrayList<Object>());
			if(rs.next()) {
				role.setRole(rs.getString("role"));
				role.setRoleID(rs.getInt("roleID"));
			}
		} catch (Exception e) {
			System.out.println("获取权限错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return role;
	}
}
