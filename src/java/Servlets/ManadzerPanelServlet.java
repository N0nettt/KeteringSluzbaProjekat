/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Prodaja;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author N0net
 */
public class ManadzerPanelServlet extends HttpServlet {

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
        Connection con;
        Statement st;
        ResultSet rs1, rs2;
        ArrayList<Prodaja> sveProdaje = new ArrayList<>(); 
        HashMap<String, Integer> najvernijiKorisnici = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
            st = con.createStatement();
            String upit = "SELECT * from prodaja";
            String upit2 = "SELECT korisnikUsername, SUM(ukupnaCena) from prodaja group by korisnikUsername order by SUM(ukupnaCena) desc";
            rs1 = st.executeQuery(upit);
            while(rs1.next())
            {
                Prodaja p = new Prodaja();
                p.setUsername(rs1.getString("korisnikUsername"));
                p.setPorudzbinaID(rs1.getInt("porudzbinaID"));
                p.setCena(rs1.getInt("ukupnaCena"));
                p.setVreme(rs1.getDate("vreme"));     
                sveProdaje.add(p);
            }
            rs2 = st.executeQuery(upit2);
            while(rs2.next()){
                najvernijiKorisnici.put(rs2.getString("korisnikUsername"), rs2.getInt("SUM(ukupnaCena)"));
            }

            con.close();
            request.setAttribute("sveProdaje", sveProdaje);
            request.setAttribute("najvernijiKorisnici", najvernijiKorisnici);
            request.getRequestDispatcher("mpanel.jsp").forward(request, response);
        }
        catch(SQLException | ClassNotFoundException e)
        {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
    }
}

   