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
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "ManagingServlet", urlPatterns = { "/managing" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class ManagingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(response.getCharacterEncoding());
        String action = request.getParameter("action"),
                sifra = request.getParameter("sifra"),
                vrsta = request.getParameter("vrsta");
        Connection con;
        Statement st;
        
        if(action.equalsIgnoreCase("remove"))
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
                st = con.createStatement();
                String upit = "DELETE from proizvod where sifra='" + sifra + "'";
                st.executeUpdate(upit);
            }
            catch(SQLException  | ClassNotFoundException e)
            {
                String error = e.getMessage();
                request.setAttribute("error", error);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            String poruka = "Proizvod je uspesno obrisan";
            if(vrsta.equals("slani"))
            {
                request.setAttribute("poruka", poruka);
                request.getRequestDispatcher("slaniproizvodi").forward(request, response);
            }
            else {
                request.setAttribute("poruka", poruka);
                request.getRequestDispatcher("slatkiproizvodi").forward(request, response);
            }
        }
        out.close();
    }


   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            request.setCharacterEncoding(response.getCharacterEncoding());
            String imeProizvoda = request.getParameter("ime"),
                    opis = request.getParameter("opis"),
                    cena = request.getParameter("cena"),
                    vrsta = request.getParameter("vrstaProizvoda"),
                    sifra = request.getParameter("sifra"),
                    prethodnaStrana = request.getParameter("hidden_prethodna");
                    
            Part filePart = request.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            Connection con;
            Statement st;
            if(imeProizvoda!=null && imeProizvoda.length()>0 && opis!=null && opis.length()>0 && cena!=null && cena.length()>0 && vrsta!=null && vrsta.length()>0
                    && sifra!=null && sifra.length()>0)
            {
                try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/keteringsluzba","root","");
                st = con.createStatement();
                String upit = "INSERT into proizvod(sifra, ime, slika, vrsta, opis, cena) VALUES('"+sifra+"','"+imeProizvoda+"','img/"+fileName+"','"+vrsta+"','"+opis+"','"+
                        cena+"')";
                st.executeUpdate(upit);
            }
            catch(SQLException | ClassNotFoundException e){
                String poruka = e.getMessage();
                request.setAttribute("error", poruka);
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            }else {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Morate uneti sva polja');");
                    out.println("location='apanel.jsp';");
                    out.println("</script>");
            }
            for (Part part : request.getParts()) {
            part.write("C:\\Users\\N0net\\Documents\\NetBeansProjects\\KeteringZluzba\\web\\img\\" + fileName);
            }
            if(prethodnaStrana.equals("adminpanel")){
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Proizvod uspesno dodat');");
                out.println("location='apanel';");
                out.println("</script>");
            }
            else{
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Proizvod uspesno dodat');");
                out.println("location='mpanel';");
                out.println("</script>");
            }

        }      
    }

   


