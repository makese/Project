package cn.makese.dbmanager;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

public class MyTableConvert {
	
	public<T> ArrayList<T> convertToList(ResultSet rs,Class<T> c) throws SQLException, InstantiationException, IllegalAccessException {
		ArrayList<T> list = new ArrayList<T>();
		T t = null;
		Field[] fieldS = null;
		while(rs.next()) {
			t = c.newInstance();
			fieldS = t.getClass().getDeclaredFields();
			for (Field field : fieldS) {
				field.setAccessible(true);
				field.set(t, rs.getObject(field.getName()));
			}
			list.add(t);
		}
		return list;
	}
}
