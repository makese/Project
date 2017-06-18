package cn.makese.dbmanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public interface IDataAccess {
  int executeUpdate(String sql,ArrayList<Object> parameters);
  ResultSet getResultSet(String sql,ArrayList<Object> parameters);
  PreparedStatement getPreparedStatement(String sql);
  void releaseSource();
}
