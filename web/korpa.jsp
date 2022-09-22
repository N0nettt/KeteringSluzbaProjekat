<%-- 
    Document   : korpa.jsp
    Created on : Sep 19, 2022, 7:15:50 PM
    Author     : N0net
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Item" %>
<%@page import="Beans.Proizvod" %>
<%@page import="Beans.Korisnik" %>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Item> korpa = (ArrayList<Item>)session.getAttribute("cart"); %>
<% Korisnik korisnik = (Korisnik)session.getAttribute("korisnik"); %>
<%! int ukupnaCena; %>
<html>
<head>    
  <meta charset="utf-8">
  <link rel="icon" href="img/favicon-32x32.png">
  <title>Ketering sluzba - Korpa</title>
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
    <h3 align="center">Moja korpa</h3>
    <h3 align="center">${poruka}</h3>
    </c:if> 
    <p> Kao kupac, za svaku kupovinu vecu od 10000 dinara dobijate poen. Poene mozete iskoristit u zamenu za 1000 dinara snizenja po poenu.<br>
   </p>
    <div id="divKorpa" class="divCart">
	<table class="table">
		<tr>
			<th>Ime</th>
			<th>Kolicina</th>
			<th>Cena</th>
                        <th>Vrsta</th>
                        <th>Ukupna cena</th>
			<th></th>
		</tr>
                <%  if(korpa!=null) {
                    ukupnaCena = 0;
                    for (int i=0; i<korpa.size(); i++) { %> 
		<tr>
                    <td><%= korpa.get(i).getProzivod().getIme()%></td>
		    <td><%= korpa.get(i).getKolicina()%></td>
		    <td><%= korpa.get(i).getProzivod().getCena()%></td>
                    <td><%= korpa.get(i).getProzivod().getVrsta() %></td>
                    <td><% int cena = Integer.parseInt(korpa.get(i).getProzivod().getCena()); %>
                        <%= korpa.get(i).getKolicina() * cena %></td>
                    <td><a href="cart?action=remove&sifra=<%=korpa.get(i).getProzivod().getSifra()%>"><span class="text-danger">Ukloni</span></a></td>
		</tr>
                <% 
                    int c = Integer.parseInt(korpa.get(i).getProzivod().getCena());
                    ukupnaCena += c*korpa.get(i).getKolicina();
                %>
                <% }
                }%>
		<tr>
			<td colspan="3" align="right">Ukupna cena:</td>
                        <td align="right"> <%= ukupnaCena %></td>
                        <% if(korisnik!=null) { %>
                        <td>Cena sa popustom:</td>
                        <td><% int brojPoena = Integer.parseInt(korisnik.getPoeni()); 
                               int cenaSaPopustom = ukupnaCena - brojPoena * 1000;
                               if(cenaSaPopustom >= 0) { %>
                        <%= cenaSaPopustom %>
                        <% } else { %>
                        <%= ukupnaCena %>
                        <% } %> </td>
                        <% } %>
		</tr>
                
	</table>
	<form method="post" action="placanje">
            <% if(korisnik!=null) { %><p>Broj ostvarenih poena: <%= korisnik.getPoeni() %><br> <label for="iskoristiPoene">Da li zelite da iskoristite poene?</label><input type="radio" id="iskoristiPoene" name="iskoristiPoene" value="da">
                <% } %></p><br>
	<input  type="submit" id="btnPlati" name="plati" class="btn btn-default" value="Plati">	
	</form>
</div>
    <div w3-include-html="footer.html"></div>
  <div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="js/mojJs.js"></script>
 <script>
    includeHTML();
  </script>
</body>
</html>