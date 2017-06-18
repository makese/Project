package cn.makese.dbmanager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public class ConnectionPool {
	private LinkedList<Connection> connectionsPool;
	private String url = "jdbc:sqlserver://localhost:1433; DatabaseName=project";
	private String username = "sa";
	private String password = "123456";
	private String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private int poolSize = 30;
	private static ConnectionPool instance = null;
	
	private ConnectionPool() {
		Init();
	}

	private void Init() {
		connectionsPool = new LinkedList<Connection>();
		addConnection();
	}

	private void addConnection() {
		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driverClassName);
				Connection coon = java.sql.DriverManager.getConnection(url,username,password);
				connectionsPool.add(coon);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized void closePool() {
		for (int i = 0; i < connectionsPool.size(); i++) {
			try {
				((Connection)connectionsPool.get(i)).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			connectionsPool.remove(i);
		}
	}
	
	public static ConnectionPool getInstance() {
		if(instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	public synchronized Connection getConnection() {
		if(connectionsPool.size() > 0) {
			Connection conn = connectionsPool.getFirst();
			connectionsPool.removeFirst();
			System.out.println("连接池剩余" + connectionsPool.size() + "个连接");
			return conn;
		} else {
			addConnection();
			return getConnection();
		}
	}
	
	public synchronized void release(Connection conn) {
		connectionsPool.addLast(conn);
	}
}

