<%-- 
    Document   : apanel
    Created on : Sep 21, 2022, 3:02:06 PM
    Author     : N0net
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Korisnik" %>
<%@page import="java.util.ArrayList" %>
<% Korisnik k = (Korisnik)session.getAttribute("korisnik");%>
<% if(k==null || !k.getRole().equals("Admin")) { %>
<% response.sendRedirect("index.jsp"); %>
<% } %>
<% ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>)request.getAttribute("korisnici"); %>
<!DOCTYPE html>
<html>
    <head>    
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon-32x32.png">
  <title>Ketering sluzba - Admin Panel</title>
   <link rel="icon" href="img/favicon.jpg">
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/mojstil.css">
  <link rel="stylesheet" type="text/css" href="css/korpastil.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="js/includeScript.js"></script>
</head>
<body>
  <div class="container">
    <div w3-include-html="header.jsp"></div>
    <div class="row">
	<div class="col-md-12">
            <fieldset>
                <legend>Dodaj proizvod:<br>
                ${poruka}</legend>
		<form method="post" enctype="multipart/form-data" action="managing">
                    <div class="form-group">
			<label for="ime">Sifra proizvoda:</label>
			<input id="sifra" type="text" name="sifra" class="form-control" >
                    </div>    
                    <div class="form-group">
			<label for="ime">Ime proizvoda:</label>
			<input id="ime" type="text" name="ime" class="form-control" >
					
                    </div>    
		<div class="form-group">
			<label for="opis">Opis proizvoda:</label>
			<input id="opis" type="text" name="opis" class="form-control" >
		</div>
		<div class="form-group">
			<label for="cena">Cena proizvoda:</label>
			<input id="cena" type="text" name="cena" class="form-control" >
					
		</div>
		<div class="form-group">
			<select name="vrstaProizvoda" id="vrstaProizvoda">
			<option value="Slana hrana">Slana Hrana</option>
			<option value="Slatka hrana">Slatka Hrana</option>
			</select>		
		</div>
                <input type="hidden" name="hidden_prethodna" value="adminpanel"/>
                <input type="file" name="file" />
		<input type="submit" class="btn btn-default" value="Dodaj proizvod"/>
                </form>
            </fieldset><br>
        </div>     
        <% if(korisnici!=null) { %>
        <div class="row">
            <div class="col-md-12">
		<fieldset>
		<legend>Korisnici:</legend>	
		<table class="table">
                    <tr><th>Username</th><th>Email</th><th>Role</th><th>Brisanje</th><th colspan="3">Promeni role</th></tr>
                <% for(int i=0; i<korisnici.size();i++) { %>
                    <tr><td><%=korisnici.get(i).getUsername()%></td><td><%=korisnici.get(i).getEmail()%></td><td><%=korisnici.get(i).getRole()%></td><td><a href="korisnici?action=obrisi&username=<%=korisnici.get(i).getUsername()%>"><span class='text-danger'>Obrisi korisnika</span></a></td>
                        <td><a href="korisnici?action=promeni&role=Klijent&username=<%=korisnici.get(i).getUsername()%>"><span class='text-danger'>Promeni u klijent</span></a></td>
                        <td><a href="korisnici?action=promeni&role=Menadzer&username=<%=korisnici.get(i).getUsername()%>"><span class='text-danger'>Promeni u menadzer</span></a></td>
                        <td><a href="korisnici?action=promeni&role=Admin&username=<%=korisnici.get(i).getUsername()%>"><span class='text-danger'>Promeni u admin</span></a></td>
                <% } %>
       <% } %>
       </table>
	</fieldset>
	</div>
	</div>
       
    <div w3-include-html="footer.html"></div>
<script>
includeHTML();
</script>
</body>
</html>
