package com.securekloud;

public abstract class XmlData {
	static String filePath = "C:\\Users\\pavis\\eclipse-workspace\\XmlAssignment\\src\\main\\resources";
	static String tag = "persondata";
	static String xml1Filepath = filePath+"\\geodata.xml";
	static String xml2Filepath = filePath+"\\salarydata.xml";
	static String outputXmlFilePath = filePath+"\\"+tag+".xml";
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String databaseURL = "jdbc:mysql://localhost:3306/sample_data?autoReconnect=true&useSSL=false";
	static String user = "root";
	static String password = "Welcome@1";
	static String insertQuery = "INSERT  INTO  person (pname, paddress, pno, psalary)  VALUES  (?,?,?,?)";
}
