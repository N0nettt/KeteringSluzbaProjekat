/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Proizvod;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author N0net
 */
public class SlatkiProizvodiServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(response.getCharacterEncoding());
        HttpSession sesija = request.getSession();
        ArrayList<Proizvod> proizvodi = new ArrayList<>();
        Connection con = null;
        Statement st;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba", "root", "");
            st = con.createStatement();
            String upit = "SELECT * from proizvod where vrsta ='Slatka hrana'";
            rs = st.executeQuery(upit);
            while(rs.next()){
                Proizvod p = new Proizvod();
                p.setCena(rs.getString("cena"));
                p.setIme(rs.getString("ime"));
                p.setOpis(rs.getString("opis"));
                p.setSifra(rs.getString("sifra"));
                p.setSlika(rs.getString("slika"));
                p.setVrsta(rs.getString("vrsta"));
                proizvodi.add(p);
            }
            con.close();
            st.close();
            rs.close();
            sesija.setAttribute("slatkiProizvodi", proizvodi);
            request.getRequestDispatcher("slatkiproizvodi.jsp").forward(request, response);
        }
        catch(SQLException e)
        {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);                
        }
        catch(ClassNotFoundException ex)
        {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response); 
           
        }   
    }
}
    
