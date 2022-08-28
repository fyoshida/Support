package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

abstract public class DataBaseManager {
	static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private final String dburl;

	private final String dbuser;

	private final String dbpassword;

	public DataBaseManager(String dataBaseName,String userName,String passWord) {
		this.dburl="jdbc:sqlserver://mis.nagaokaut.ac.jp;DataBaseName="+dataBaseName;
		this.dbuser=userName;
		this.dbpassword=passWord;
	}

	public void updateRecord(String query) {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
	}

	public Object getRecord(String sql) {
		Connection con = null;
		Object object = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				object = copyRecord(rs);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
		return object;
	}

	public LinkedList getRecords(String sql) {
		Connection con = null;
		LinkedList list = new LinkedList();
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(dburl, dbuser, dbpassword);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Object object = copyRecord(rs);
				list.addLast(object);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException es) {
				es.printStackTrace();
			}
		}
		return list;
	}
	abstract public Object copyRecord(ResultSet rs) throws Exception;

}
