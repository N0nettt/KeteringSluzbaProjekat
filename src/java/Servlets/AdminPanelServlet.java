/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import Beans.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author N0net
 */
@WebServlet(name = "AdminPanelServlet", urlPatterns = {"/AdminPanelServlet"})
public class AdminPanelServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(response.getCharacterEncoding());
        Connection con;
        Statement st;
        ResultSet rs;
        ArrayList<Korisnik> korisnici = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
            st = con.createStatement();
            String upit = "Select * from korisnik";
            rs = st.executeQuery(upit);
            while(rs.next())
            {
                Korisnik k = new Korisnik();
                k.setEmail(rs.getString("email"));
                k.setUsername(rs.getString("username"));
                k.setRole(rs.getString("role"));
                k.setPoeni("0");
                k.setPassword("");
                korisnici.add(k);
            }
        }
        catch(SQLException | ClassNotFoundException e)
        {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        request.setAttribute("korisnici", korisnici);
        request.getRequestDispatcher("apanel.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
