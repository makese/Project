package cn.makese.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import cn.makese.dbmanager.DataAccessFactory;
import cn.makese.dbmanager.EnumDatabaseType;
import cn.makese.dbmanager.IDataAccess;

public class BasicDAO<T> implements IBasicDAO<T> {
	protected String tableName;
	public static EnumDatabaseType databaseType = EnumDatabaseType.Sqlserver;
	protected IDataAccess dataAccess = DataAccessFactory.CreateDataAccess(databaseType);

	public EnumDatabaseType getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(EnumDatabaseType databaseType) {
		BasicDAO.databaseType = databaseType;
	}

	@Override
	public int Save(T t) throws Exception {
		tableName = t.getClass().getSimpleName();
		Field[] properties = t.getClass().getDeclaredFields();
		ArrayList<Object> qList = new ArrayList<Object>();
		StringBuilder fieldSB = new StringBuilder();
		StringBuilder valueSB = new StringBuilder();
		StringBuilder SqlSB = new StringBuilder();
		for (Field propertie : properties) {
			propertie.setAccessible(true);
			if (IsValueBlank(propertie.get(t))) {
				continue;
			}
			fieldSB.append(propertie.getName() + ",");
			valueSB.append("?,");
			qList.add(propertie.get(t));
		}
		fieldSB.deleteCharAt(fieldSB.length() - 1);
		valueSB.deleteCharAt(valueSB.length() - 1);
		SqlSB.append("insert into ");
		SqlSB.append(tableName);
		SqlSB.append("(");
		SqlSB.append(fieldSB);
		SqlSB.append(") values(");
		SqlSB.append(valueSB);
		SqlSB.append(")");
		return dataAccess.executeUpdate(SqlSB.toString(), qList);
	}

	@Override
	public int Delete(T t) throws Exception {
		tableName = t.getClass().getSimpleName();
		Field[] properties = t.getClass().getDeclaredFields();
		ArrayList<Object> qList = new ArrayList<Object>();
		StringBuilder fieldSB = new StringBuilder();
		StringBuilder SqlSB = new StringBuilder();
		for (Field propertie : properties) {
			propertie.setAccessible(true);
			if (IsValueBlank(propertie.get(t))) {
				continue;
			}
			qList.add(propertie.get(t));
			fieldSB.append(" and " + propertie.getName() + "=?");
		}
		SqlSB.append("delete from ");
		SqlSB.append(tableName);
		SqlSB.append(" where 1=1");
		SqlSB.append(fieldSB);
		return dataAccess.executeUpdate(SqlSB.toString(), qList);
	}

	@Override
	public int Change(T t) throws Exception {
		tableName = t.getClass().getSimpleName();
		ArrayList<Object> qList = new ArrayList<Object>();
		Field[] properties = t.getClass().getDeclaredFields();
		StringBuilder fieldSB = new StringBuilder();
		StringBuilder SqlSB = new StringBuilder();
		for (int i = 1; i < properties.length; i++) {
			properties[i].setAccessible(true);
			if (IsValueBlank(properties[i].get(t))) {
				continue;
			}
			fieldSB.append(properties[i].getName() + "=?,");
			qList.add(properties[i].get(t));
		}
		properties[0].setAccessible(true);
		qList.add(properties[0].get(t));
		fieldSB.deleteCharAt(fieldSB.length() - 1);
		SqlSB.append("update ");
		SqlSB.append(tableName);
		SqlSB.append(" set ");
		SqlSB.append(fieldSB);
		SqlSB.append(" where ");
		SqlSB.append(properties[0].getName());
		SqlSB.append("=?");
		return dataAccess.executeUpdate(SqlSB.toString(), qList);
	}

	@Override
	public ResultSet FindAll(T t) throws Exception {
		return BasicFind(t, "FindHaveNoConditon");
	}

	@Override
	public ResultSet FindByEqual(T t) throws Exception {
		return BasicFind(t, "FindHaveEqualCondition");
	}

	@Override
	public ResultSet FindByLike(T t) throws Exception {
		return BasicFind(t, "FindHaveLikeEqualCondition");
	}

	@Override
	public ResultSet FindByBetween(T t) {
		tableName = t.getClass().getSimpleName();
		return null;
	}

	private ResultSet BasicFind(T t, String getConditionStr) throws Exception {
		tableName = t.getClass().getSimpleName();
		ArrayList<Object> qList = new ArrayList<Object>();
		Field[] properties = t.getClass().getDeclaredFields();
		StringBuilder SqlSB = new StringBuilder();
		StringBuilder fieldSB = new StringBuilder();
		String selectfieldSB = "";
		for (Field propertie : properties) {
			fieldSB.append(propertie.getName() + ",");
			propertie.setAccessible(true);
			Object obj = propertie.get(t);
			String conditionStr = this.getClass().getMethod(getConditionStr, Type.class, String.class, Object.class)
					.invoke(this.getClass().getName(), propertie.getGenericType(), propertie.getName(), obj).toString();
			if (!StringUtils.isBlank(conditionStr)) {
				qList.add(obj);
			}
			selectfieldSB = selectfieldSB + conditionStr;
		}
		fieldSB.deleteCharAt(fieldSB.length() - 1);
		SqlSB.append("select ");
		SqlSB.append(fieldSB);
		SqlSB.append(" from ");
		SqlSB.append(tableName);
		SqlSB.append(" where 1=1");
		if (!StringUtils.isBlank(selectfieldSB)) {
			SqlSB.append(selectfieldSB);
		}
		return dataAccess.getResultSet(SqlSB.toString(), qList);
	}

	public static String FindHaveNoConditon(Type type, String propertyName, Object obj) {
		return "";
	}

	public static String FindHaveEqualCondition(Type type, String propertyName, Object obj) {
		StringBuilder selectFieldSB = new StringBuilder();
		String fieldTypeStr = type.toString();
		if (fieldTypeStr.equals("class java.lang.String")) {
			if (obj == null) {
				return "";
			}
		} else if (fieldTypeStr.equals("class java.util.Date")) {
			return "";
		} else {
			Number temp = (Number) obj;
			if (temp.doubleValue() < 0) {
				return "";
			}
		}
		selectFieldSB.append(" and ");
		selectFieldSB.append(propertyName);
		selectFieldSB.append("=?");
		return selectFieldSB.toString();
	}

	public static String FindHaveLikeEqualCondition(Type type, String propertyName, Object obj) {
		StringBuilder selectFieldSB = new StringBuilder();
		String fieldTypeStr = type.toString();
		if (fieldTypeStr.equals("class java.lang.String")) {
			if (obj == null) {
				return "";
			}
		} else if (fieldTypeStr.equals("class java.util.Date")) {
			return "";
		} else {
			Number temp = (Number) obj;
			if (temp.doubleValue() < 0) {
				return "";
			}
		}

		selectFieldSB.append(" and ");
		selectFieldSB.append(propertyName);
		selectFieldSB.append(" like ?");
		return selectFieldSB.toString();
	}

	public Boolean IsValueBlank(Object value) {
		if (value == null) {
			return true;
		} else if (value instanceof String) {
			return false;
		} else if (value instanceof Date) {
			return true;

		} else {
			Number temp = (Number) value;
			if (temp.doubleValue() < 0)
				return true;
		}
		return false;
	}

	public void releaseSource() {
		try {
			dataAccess.releaseSource();
		} catch (Exception e) {
			System.out.println("ÊÍ·Å×ÊÔ´´íÎó");
			e.printStackTrace();
		}
		
	}
}
