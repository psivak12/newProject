package com.securekloud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XmlLoad {
	
	static Connection conn = null;
	PreparedStatement prepInsertStmt = null;
	
	public void getConnection() throws SQLException {
	    try {
	    	Class.forName(XmlData.driver);
	        conn = DriverManager.getConnection(XmlData.databaseURL, XmlData.user, XmlData.password);
	        if (conn != null) {
	            System.out.println("Connected to the DB");
	        }
	        
	    } catch (SQLException | ClassNotFoundException ex) {
	        System.out.println("An error occurred");
	        ex.printStackTrace();
		}
	}
	
	public void closeConnection() {
		if (conn != null) {
	        try {
	            conn.close();
	        } catch (SQLException e) { }
	    }
	}
	
	public void addToDB(String name, String address, String phno, String salary, String Pension) {
		try {
			if(conn != null) {
				prepInsertStmt = conn.prepareStatement(XmlData.insertQuery);
				prepInsertStmt.setString(1, name);
				prepInsertStmt.setString(2, address);
				prepInsertStmt.setString(3, phno);
				prepInsertStmt.setString(4, salary);
				prepInsertStmt.executeUpdate();
				System.out.println("Inserted successfully");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public StringBuilder getDetails(String name, String address, String phno, String salary) {
		try {    
			if(conn != null) {
				String getDetails = "SELECT * FROM PERSON WHERE ";

				getDetails = (name != "") ? getDetails+"pname LIKE '%"+name+"%' AND " : getDetails+"pname IS NOT NULL AND ";
				getDetails = (address != "") ? getDetails+"paddress LIKE '%"+address+"%' AND " : getDetails+"paddress IS NOT NULL AND ";
				getDetails = (phno != "") ? getDetails+"pno LIKE '%"+phno+"%' AND " : getDetails+"pno IS NOT NULL AND ";
				getDetails = (salary != "") ? getDetails+"psalary LIKE '%"+salary+"%' " : getDetails+"psalary IS NOT NULL ";
				
				prepInsertStmt = conn.prepareStatement(getDetails);

				ResultSet rs=prepInsertStmt.executeQuery();
				StringBuilder table = new StringBuilder("<table class=\"table table-striped\">"
						+ "<tr> <th scope=\"col\">#</th> <th scope=\"col\">Name</th> <th scope=\"col\">Address</th> <th scope=\"col\">Phone Number</th> <th scope=\"col\">Salary</th> </tr>");
				int i=1;
				while(rs.next()){  
					System.out.println(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));  
					table.append("<tr> <th scope=\"row\">"+i+"</th> <td>"+rs.getString(2)+"</td> <td>"+rs.getString(3)+"</td> <td>"+rs.getString(4)+"</td> <td>"+rs.getString(5)+"</td> </tr>");
					i++;
				}
				table.append("</table>");
				return table;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
