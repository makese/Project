package cn.makese.dao;
import java.sql.ResultSet;
///by makese 
public interface IBasicDAO<T> {
	int Save(T t) throws Exception;
	int Delete(T t) throws Exception;
	int Change(T t) throws Exception;
	ResultSet FindAll(T t) throws Exception;
	ResultSet FindByEqual(T t) throws Exception;
	ResultSet FindByLike(T t) throws Exception;
	ResultSet FindByBetween(T t);
	void releaseSource() throws Exception;
	
}
