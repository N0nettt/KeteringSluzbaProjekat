<%-- 
    Document   : register.jsp
    Created on : Sep 15, 2022, 1:43:06 PM
    Author     : N0net
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Korisnik"%>
<% Korisnik k = (Korisnik)session.getAttribute("korisnik"); 
   if(k != null) {
       response.sendRedirect("index.jsp");
   }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon-32x32.png">
  <title>Ketering služba - Registracija</title>
   <link rel="icon" href="img/favicon.jpg">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/mojstil.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="js/includeScript.js"></script>
</head> 
<body>
  <div class="container">
  <div w3-include-html="header.jsp"></div>
  <div class="row">
	<div class="col-md-6">
		<div class="wrapper">
			<h2>Registracija</h2>
                        <% String poruka = (String)request.getAttribute("poruka"); %>
                        <% if(poruka != null) { %>
                        <div class="alert alert-danger"><%= poruka %></div>
                        <% } %>
			<form action="register" method="post" novalidate>
				<div class="form-group">
					<label for="user">Username</label>
					<input id="user" type="text" name="username" class="form-control" >
					
				</div>    
				<div class="form-group">
					<label for="password">Password</label>
					<input id="pw" type="password" name="password" class="form-control" >
					</span
				</div>
				<div class="form-group">
					<label for="confirmpassword">Potvrdite password</label>
					<input id="confirmpassword" type="password" name="cpassword" class="form-control" >
					
				</div>
				<div class="form-group">
					<label for="mail">Email</label>
					<input id="mail" type="email" name="email"  class="form-control" >	
				</div>
				<div class="form-group">
					<input type="submit" name="register" class="btn btn-default" value="Register">
					<a href="login.jsp"><button class="btn btn-default" type="button">Login</button></a>
				</div>
				
			</form>
		</div>
	</div>
	<div class="col-md-6">
	</div>
</div>
  <div w3-include-html="footer.html"></div>
</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script>
    includeHTML();
  </script>
</body>
</html>