/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import Beans.Korisnik;
import javax.servlet.http.HttpSession;


/**
 *
 * @author N0net
 */
public class LoginServlet extends HttpServlet {

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
        sesija.setMaxInactiveInterval(15*60);
        Korisnik k = new Korisnik();
        String username = request.getParameter("username"),
                password = request.getParameter("password");
        Connection con;
        Statement st;
        ResultSet rs;
        String poruka;
        
        try {
            if(username!=null && username.length()>0 && password!=null && password.length()>0)
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
                st = con.createStatement();
                String upit = "SELECT * from korisnik where username='" + username + "' and password ='" + password + "'";
                rs = st.executeQuery(upit);
                if(rs.next())
                {
                    k.setEmail(rs.getString("email"));
                    k.setPassword(rs.getString("password"));
                    k.setPoeni(rs.getString("bodovi"));
                    k.setUsername(rs.getString("username"));
                    k.setRole(rs.getString("role"));
                    
                    sesija.setAttribute("korisnik", k);
                    response.sendRedirect("index.jsp");
                }
                else {
                    poruka = "Pogresan username ili password!";
                    request.setAttribute("poruka", poruka);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                
            }
            else {
                poruka = "Morate popuniti sva polja";
                request.setAttribute("poruka", poruka);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
        catch(SQLException e)
        {
            String error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }catch(ClassNotFoundException ex) {}
        
       
        
    }

}
