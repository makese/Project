package cn.makese.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class SqlServer implements IDataAccess {
	private Connection connection;
	private ResultSet resultSet;
	private PreparedStatement preparedStatement;
	private static ConnectionPool pool;
	
	public SqlServer() {
		pool = ConnectionPool.getInstance();
	}
	public int executeUpdate(String sql, ArrayList<Object> parameters) {
		int val=0;
		preparedStatement= getPreparedStatement(sql);
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
		resultSet=null;
		preparedStatement= getPreparedStatement(sql);
		try {
			PrepareCommand(preparedStatement, connection, parameters);
			resultSet=preparedStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("executeQuery操作异常");
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			connection = pool.getConnection();
			preparedStatement=connection.prepareStatement(sql);
			return preparedStatement;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取预编译语句对象错误");
			return null;
		}
		
	}
	public static void PrepareCommand(PreparedStatement preparedStatement,Connection connection,ArrayList<Object> parameters) throws SQLException {
			try {
				  	if(connection.isClosed())
				  	{
					  connection = pool.getConnection();
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
		try {
			if (resultSet != null)
				resultSet.close();
			if (preparedStatement != null)
				preparedStatement.close();
			if (connection != null)
				pool.release(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("关闭数据库连接错误");
		}
		
	}
}
