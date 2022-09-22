<%-- 
    Document   : slatkirpoizvodi
    Created on : Sep 18, 2022, 3:35:02 PM
    Author     : N0net
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Proizvod" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Beans.Korisnik" %>
<% ArrayList<Proizvod> slatkiProizvodi = (ArrayList<Proizvod>)session.getAttribute("slatkiProizvodi"); %>
<% Korisnik k = (Korisnik)session.getAttribute("korisnik"); %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon.jpg">
  <title>Slatka hrana</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/all.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/mojstil.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="js/includeScript.js"></script>
</head>
<body>
  <div class="container">
    <div w3-include-html="header.jsp"></div>
    <h3 align="center">${poruka}</h3>
    <div class="row">
         <% for(int i=0; i<slatkiProizvodi.size(); i++) { %>
         <div class="col-md-4">
         <form method="post" action="cart">
         <div class="thumbnail">
             <img src="<%= slatkiProizvodi.get(i).getSlika() %>" alt="img">
         <div class="caption">
             <p><%= slatkiProizvodi.get(i).getIme() %><br><%= slatkiProizvodi.get(i).getOpis()%><br>
             <%=slatkiProizvodi.get(i).getCena()%> rsd</p>
         <input type="hidden" name="hidden_id" value="<%= slatkiProizvodi.get(i).getSifra()%>"/>
         <input type="hidden" name="hidden_ime" value="<%= slatkiProizvodi.get(i).getIme()%>"/>
         <input type="hidden" name="hidden_vrsta" value="<%= slatkiProizvodi.get(i).getVrsta()%>"/>
         <input type="hidden" name="hidden_opis" value="<%= slatkiProizvodi.get(i).getOpis()%>"/>
         <input type="hidden" name="hidden_cena" value="<%= slatkiProizvodi.get(i).getCena()%>"/>
         <input type="hidden" name="hidden_action" value="buy"/>
         <% if(k!=null && (k.getRole().equals("Admin") || k.getRole().equals("Menadzer"))) { %>
         <a href="managing?action=remove&vrsta=slatki&sifra=<%=slatkiProizvodi.get(i).getSifra()%>"><span class='text-danger'>Obrisi iz baze</span></a><br>
         <% } %>
         <input type='submit' name='add_to_cart' class='btn btn-info' value='Add to cart'/><br>
         </div>
         </div>
         </form>
         </div>
         <% } %>
    <div w3-include-html="footer.html"></div>
  </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="js/mojJs.js"></script>
  <script>
    includeHTML();
  </script>
</body>
</html>