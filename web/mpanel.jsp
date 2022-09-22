<%-- 
    Document   : mpanel
    Created on : Sep 21, 2022, 3:01:36 PM
    Author     : N0net
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><%@page import="Beans.Korisnik" %>
<%@page import="Beans.Prodaja" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.HashMap" %>
<% HashMap<String, Integer> najvernijiKorisnici = (HashMap<String, Integer>)request.getAttribute("najvernijiKorisnici"); %>
<% ArrayList<Prodaja> sveProdaje = (ArrayList<Prodaja>)request.getAttribute("sveProdaje"); %>
<% Korisnik k = (Korisnik)session.getAttribute("korisnik");%>
<% if(k==null || !k.getRole().equals("Menadzer")) { %>
<% response.sendRedirect("index.jsp"); %>
<% } %>
<!DOCTYPE html>
<html>
    <head>    
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon-32x32.png">
  <title>Ketering sluzba - Menadzer Panel</title>
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
                <input type="hidden" name="hidden_prethodna" value="menadzerpanel"/>
                <input type="file" name="file" />
		<input type="submit" class="btn btn-default" value="Dodaj proizvod"/>
                </form>
            </fieldset><br>
        </div>    
    </div>
    
    <div class="row">
	<div class="col-md-6">
            <fieldset>
		<legend>Porudzbine:</legend>
                    <table class="table">
			<tr><th>PourdzbinaID</th><th>Korisnik</th><th>Cena</th><th>Vreme</th></tr>
			<% if(sveProdaje!=null && sveProdaje.size()>0) {
                            for(int i=0; i<sveProdaje.size(); i++) {%>
                        <tr><td><%=sveProdaje.get(i).getPorudzbinaID()%></td><td><%=sveProdaje.get(i).getUsername()%></td><td><%=sveProdaje.get(i).getCena()%></td>
                            <td><%=sveProdaje.get(i).getVreme()%></td>
                        <% } } %>
		</table>
            </fieldset>
	</div>       
        <div class="col-md-6">
            <fieldset>
		<legend>Najverniji korisnici:</legend>
                    <table class="table">
			<tr><th>Korisnik</th><th>Potroseno</th></tr>
			<%  if(!najvernijiKorisnici.isEmpty()) {  
                            for(Map.Entry<String, Integer> e : najvernijiKorisnici.entrySet()) { %>
                        <tr><td><%= e.getKey() %></td><td><%= e.getValue() %></td>
                        <% } } %>
                    </table>
            </fieldset>
	</div>
    <div w3-include-html="footer.html"></div>
<script>
includeHTML();
</script>
</body>
</html>
