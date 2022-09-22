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

/**
 *
 * @author N0net
 */
public class RegisterServlet extends HttpServlet {

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
        String username = request.getParameter("username"),
                password = request.getParameter("password"),
                cPassword = request.getParameter("cpassword"),
                email = request.getParameter("email");
        Connection con;
        Statement st;
        ResultSet rs;
        String poruka;
        String error;
        
        try{
            if(username!=null && username.length()>0 && password!=null && password.length()>0 && cPassword!=null && cPassword.length()>0 && email!=null &&
                    email.length()>0)
            {
                if(password.equals(cPassword) && password.length()>8)
                {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba", "root", "");
                String upit = "SELECT * from korisnik where username='" + username + "'";
                st = con.createStatement();
                rs = st.executeQuery(upit);
                if(rs.next())
                {
                    poruka = "Korisncko ime je zauzeto!";
                    request.setAttribute("poruka", poruka);
                    request.getRequestDispatcher("register.jsp").forward(request, response);   
                }
                else {
                    upit = "INSERT into korisnik VALUES('" + username + "','" + password + "','" + email + "', 'Klijent', '0')";
                    st.executeUpdate(upit);
                    response.sendRedirect("login.jsp");
                }
                }
                else {
                    poruka = "Password mora imati vise od 8 karaktera i mora biti identican kao potvrda passworda!";
                    request.setAttribute("poruka", poruka);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
               
            }
            else {
                poruka = "Morate popuniti sva polja da biste se registrovali!";
                request.setAttribute("poruka", poruka);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
                 
        }
        catch(SQLException e){
            error = e.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        catch(Exception ex){
            error = ex.getMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
       
        }
    }


   
