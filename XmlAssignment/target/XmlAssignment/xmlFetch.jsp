<%@page import="com.securekloud.*" %>
<% 
	String name=request.getParameter("name");
	String address=request.getParameter("address");
	String phno=request.getParameter("phno");
	String salary=request.getParameter("salary");

	if(name.isEmpty() && address.isEmpty() && phno.isEmpty() && salary.isEmpty())
	{  
		out.print("Please enter atleast one value");  
	}else{ 
		try{
			XmlLoad xmlload = new XmlLoad(); 	
			xmlload.getConnection();
			out.print(xmlload.getDetails(name, address, phno, salary));
			xmlload.closeConnection();
		} catch(Exception e){
			out.println("Exception: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
%>