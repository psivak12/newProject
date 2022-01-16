<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>XML search</title>
<link rel="stylesheet"
href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script
src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>  
	var request;  
	function sendReq()  
	{   
	var name = $('#name').val();
	var address = $('#address').val();
	var phno = $('#phno').val();
	var salary = $('#salary').val();
			
	var url="xmlFetch.jsp?";
	
	if(name != null || name != "") 
		url=url+"name="+name+"&";
	if(address != null || address != "") 
		url=url+"address="+address+"&";
	if(phno != null || phno != "") 
		url=url+"phno="+phno+"&";
	if(salary != null || salary != "") 
		url=url+"salary="+salary;
	
	if(window.XMLHttpRequest){  
		request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject){  
		request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
	  
	try{  
		request.onreadystatechange=getRes;  
		request.open("GET",url,true);  
		request.send();  
	}catch(e){
		alert("Unable to connect to server");}  
	}  
	  
	function getRes(){  
		if(request.readyState==4){  
			var val=request.responseText;  
			document.getElementById('result').innerHTML=val;  
		}  
	}  
	  
</script> 
</head>
<body>
<div class="container">	
	<div class="form-group table-responsive" align="center">
	<h1> XML Search</h1>
	<form>
		<table>
		<tr>
			<td><label for="name">Name: </label></td>
			<td><input type="text" id="name" class="form-control"></td>
		</tr>
		<tr>
			<td><label for="address" >Address: </label></td>
			<td><input type="text" id="address" class="form-control"></td>
		</tr>
		<tr>
			<td><label for="phno">Phone Number: </label></td>
			<td><input type="text" id="phno" class="form-control"></td>
		</tr>
		<tr>
			<td><label for="salary">Salary: </label></td>
			<td><input type="text" id="salary" class="form-control"></td>
		</tr>
		<tr>
			<td><button id="submit"type="button" class="btn btn-primary" onclick="sendReq()">Submit</button></td>
		</tr>	
		</table>
	</form>
</div>
<div class="row">
	<div id="result" align="center"></div>
</div>
</div>

</body>
</html>
