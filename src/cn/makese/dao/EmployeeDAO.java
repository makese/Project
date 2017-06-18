package cn.makese.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import cn.makese.dbmanager.MyTableConvert;
import cn.makese.model.Employee;

public class EmployeeDAO extends BasicDAO<Employee> {
	public Employee findEmployeeBySno(String sno) {
		Employee employee = new Employee();
		employee.setSno(sno);
		ResultSet rs = null;
		try {
			rs = FindByEqual(employee);
			if(rs.next()) {
				employee.setID(rs.getString("id"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setTel(rs.getString("tel"));
				employee.setSalary(rs.getInt("salary"));
				
			}
		} catch (Exception e) {
			System.out.println("查找员工错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return employee;
	}
	public Employee findEmpoyeeByID(String ID) {
		Employee employee = new Employee();
		employee.setID(ID);
		ResultSet rs = null;
		try {
			rs = FindByEqual(employee);
			if(rs.next()) {
				employee.setSno(rs.getString("sno"));
				employee.setName(rs.getString("name"));
				employee.setGender(rs.getString("gender"));
				employee.setTel(rs.getString("tel"));
				employee.setSalary(rs.getInt("salary"));
				
			}
		} catch (Exception e) {
			System.out.println("查找员工错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return employee;
	}
	public ArrayList<Employee> findAllEmployee() {
		ResultSet rs;
		ArrayList<Employee> employees = new ArrayList<Employee>();
		MyTableConvert myTableConvert = new MyTableConvert();
		
		try {
			rs = FindAll(new Employee());
			employees = (ArrayList<Employee>) myTableConvert.convertToList(rs,new Employee().getClass());
		} catch (Exception e) {
			System.out.println("获取全部员工错误");
			e.printStackTrace();
		} finally {
			releaseSource();
		}
		return employees;
	}
}
