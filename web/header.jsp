<%-- 
    Document   : header.jsp
    Created on : Sep 17, 2022, 12:44:27 AM
    Author     : N0net
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Beans.Korisnik" %>
<% Korisnik k = (Korisnik)session.getAttribute("korisnik");%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

 <div class="hrow">
      <div class="col-md-4">
        <a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
      </div>
      <div class="col-md-4 divcenter">
        <h1>Ketering<img src="img/favicon.jpg">služba<h1>
        </div>
        <div class="col-md-4 divcenter">
          <form class="navbar-form navbar-right">
        <a href="korpa.jsp" type="button" class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-shopping-cart"></span> Korpa
        </a>
          </form>
        </div>
      </div>
    <nav class="navbar navbar-default">
  <div class="navbar-header">
  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
 <span class="sr-only">Toggle navigation</span>
 <span class="icon-bar"></span>
  <span class="icon-bar"></span>
  <span class="icon-bar"></span>
  </button>
  </div>
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  <ul class="nav navbar-nav">
  <li><a href="index.jsp">Pocetna strana</a></li>
  <li class="dropdown">
  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Meni <span class="caret"></span></a>
  
  <ul class="dropdown-menu">
 <li><a href="slaniproizvodi">Slana hrana</a></li>
 <li><a href="slatkiproizvodi">Slatka hrana</a></li>
 </ul>
  </li>
  </ul>
  <% if(k==null) { %>
  <ul class="nav navbar-nav navbar-right">
  <li><a href="login.jsp">Login</a></li>
  <li><a href="register.jsp">Registracija</a></li>
  </ul>
  <% } else { %>
  <ul class="nav navbar-nav navbar-right">
      <% if(k.getRole().equals("Menadzer")) { %>
  <li><a href="mpanel">Menadzer Panel</a></li>
  <% } else if(k.getRole().equals("Admin")) { %>
  <li><a href="apanel">Admin Panel</a></li>
  <% } %>
  <li><a href="logout">Logout, <%= k.getUsername() %></a></li>
  </ul>
  <% } %>
 </div><!-- /.navbar-collapse -->
 </nav><!-- kraj navigacije -->