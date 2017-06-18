package cn.makese.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class MySql implements IDataAccess {
	private static String connStr;
	private Connection connection;
	
	public int executeUpdate(String sql, ArrayList<Object> parameters) {
		int val=0;
		PreparedStatement preparedStatement= getPreparedStatement(sql);
		try {
			PrepareCommand(preparedStatement, connection, parameters);
			val=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("executeUpdate操作异常");
		}
		
		return val;
	}

	@Override
	public ResultSet getResultSet(String sql, ArrayList<Object> parameters) {
		ResultSet resultSet=null;
		PreparedStatement preparedStatement= getPreparedStatement(sql);
		try {
			PrepareCommand(preparedStatement, connection, parameters);
			resultSet=preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("executeQuery操作异常");
		}
		return resultSet;
	}

	@Override
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			connection = DriverManager.getConnection(connStr);
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			return preparedStatement;
		} catch (SQLException e) {
			System.out.println("获取预编译语句对象错误");
			return null;
		}
		
	}
	public static void PrepareCommand(PreparedStatement preparedStatement,Connection connection,ArrayList<Object> parameters) throws SQLException {
			try {
				  	if(connection.isClosed())
				  	{
					  connection=DriverManager.getConnection(connStr);
				 	}
			    }
			catch (SQLException e) {
				System.out.println("获取连接错误");
				}
			int index=1;
			for (Object parameter : parameters) {
				if(parameter instanceof String)
				{ 
					preparedStatement.setString(index++,parameter.toString());
				}
				 else  if(parameter instanceof Date)
				 		{
					 		preparedStatement.setDate(index++, (java.sql.Date)parameter);
				 		}else 
				 				{
				 					preparedStatement.setObject(index++, parameter);
				 				}		
			}
			
	}

	public void releaseSource() {
		// TODO Auto-generated method stub
		
	}
}
