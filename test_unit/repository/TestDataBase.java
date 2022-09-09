package repository;

public class TestDataBase {

//	static String workspacePathFileName="workspacePath.txt";
//	static String workspacePath;
//	IDataSet dataSet = null;
//
//	protected Connection getConnection() throws Exception {
//		Class.forName(DataBaseManager.driver);
//		Connection con = DriverManager.getConnection(DataBaseManager.dburl, DataBaseManager.dbuser,
//				DataBaseManager.dbpassword);
//		return con;
//	}
//
//	protected String getFirstLineFrom(File file) {
//		String str = "";
//		try {
//			if (file.exists()) {
//				FileReader fr = new FileReader(file);
//				BufferedReader br = new BufferedReader(fr);
//				str = br.readLine();
//				br.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return str;
//	}
//
//	protected String getWorkspacePath() {
//		if (workspacePath == null) {
//			workspacePath = getFirstLineFrom(new File(workspacePathFileName));
//		}
//		System.out.println(workspacePath);
//		return workspacePath;
//	}
//
//	protected IDataSet getDataSet(String filename) throws Exception {
//		File file = new File(getWorkspacePath() + filename);
//		IDataSet dataSet = new XlsDataSet(new FileInputStream(file));
//		return dataSet;
//	}
//
//	protected String getSqlForResetID(String[] tableNames) {
//		String sql = "";
//		for (int i = 0; i < tableNames.length; i++) {
//			sql += "DBCC CHECKIDENT (" + tableNames[i] + ",RESEED, 0);";
//		}
//		return sql;
//	}
//
//	protected void loadTestData(String fileName) throws Exception {
//		dataSet = getDataSet(fileName);
//	}
//
//	public void setTestData(String fileName) {
//		try {
//			loadTestData(fileName);
//			setTestData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void removeTestData(String fileName) {
//		try {
//			loadTestData(fileName);
//			removeTestData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setTestData() {
//		IDatabaseConnection connection = null;
//		try {
//			if (dataSet != null) {
//				String sql = getSqlForResetID(dataSet.getTableNames());
//
//				Connection con = getConnection();
//				connection = new DatabaseConnection(con);
//
//				Statement stmt = con.createStatement();
//				stmt.executeUpdate(sql);
//				stmt.close();
//
//				DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//			}
//		}
//	}
//
//	public void removeTestData() {
//		IDatabaseConnection connection = null;
//		try {
//			if (dataSet != null) {
//				Connection con = getConnection();
//				connection = new DatabaseConnection(con);
//				DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (connection != null)
//					connection.close();
//			} catch (SQLException e) {
//			}
//		}
//
//	}
}
