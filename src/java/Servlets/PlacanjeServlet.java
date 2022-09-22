/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Item;
import Beans.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author N0net
 */
public class PlacanjeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(response.getCharacterEncoding());
        HttpSession sesija = request.getSession();
        String koristiPoene = request.getParameter("iskoristiPoene");
        Connection con;
        Statement st;
 
        if(sesija.getAttribute("korisnik") == null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        if(sesija.getAttribute("cart") == null)
        {
            request.setAttribute("poruka", "Korpa je prazna");
            request.getRequestDispatcher("korpa.jsp").forward(request, response);
        }else {
            ArrayList<Item> cart = (ArrayList<Item>)sesija.getAttribute("cart");
            Korisnik k = (Korisnik)sesija.getAttribute("korisnik");
            if(cart.isEmpty())
            {
                request.setAttribute("poruka", "Korpa je prazna");
                request.getRequestDispatcher("korpa.jsp").forward(request, response);
            }
              int brojPoena = Integer.parseInt(k.getPoeni());
              int ukupnaCena = 0;
              boolean popust = false;
              if(koristiPoene!=null && koristiPoene.equals("da"))
                {
                    if( racunajCenu(cart) > brojPoena*1000)
                    {
                        ukupnaCena = racunajCenu(cart) - racunajPopust(brojPoena);
                        popust = true;
                    }
                    else{
                        ukupnaCena = racunajCenu(cart);
                        popust = false;
                    }
                }
              else{
                    ukupnaCena = racunajCenu(cart);
                }
            try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
            st = con.createStatement();
            String upit = "INSERT into prodaja(korisnikUsername, ukupnaCena, vreme) VALUES('" + k.getUsername() + "', '" +ukupnaCena+ "', current_timestamp())";
            st.executeUpdate(upit);
            }
            catch(SQLException | ClassNotFoundException e)
            {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            if(popust == true)
            {
                try {
                
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
                st = con.createStatement();
                String upit = "UPDATE korisnik SET bodovi=0 where username='"+k.getUsername()+"'";
                st.executeUpdate(upit);
            }
            catch(SQLException e)
            {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            }
                k.setPoeni("0");
            }if(ukupnaCena > 10000)
            {
                try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
                st = con.createStatement();
                String stariPoeni = k.getPoeni();
                int poeniint = Integer.parseInt(stariPoeni) + ukupnaCena / 10000;
                String poeni = Integer.toString(poeniint);
                String upit = "UPDATE korisnik SET bodovi="+poeni+" where username='"+k.getUsername()+"'";
                st.executeUpdate(upit);
                k.setPoeni(poeni);
            }
            catch(SQLException e)
            {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            }  
            }
            
            request.setAttribute("poruka", "Hvala na kupovini!");
            request.getRequestDispatcher("korpa.jsp").forward(request, response);
           }
        }
     
    private int racunajCenu(ArrayList<Item> cart)
    {
        int cena = 0;
        for(int i=0; i<cart.size(); i++)
        {
            cena += cart.get(i).getKolicina() * Integer.parseInt(cart.get(i).getProzivod().getCena());
        }
        return cena;
    }
    
    private int racunajPopust(int brojPoena)
    {
        int popust = brojPoena * 1000;
        return popust;
    }
}



