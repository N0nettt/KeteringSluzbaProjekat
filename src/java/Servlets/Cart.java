/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Beans.Item;
import Beans.Proizvod;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Cart extends HttpServlet {

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
        String action = request.getParameter("hidden_action");
        HttpSession sesija = request.getSession();
        
        if(sesija.getAttribute("korisnik") == null)
        {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
        if(action.equalsIgnoreCase("buy")){
            doPost_Buy(request, response);
        }
    }
    
    protected void doPost_Buy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Proizvod p = new Proizvod();
        HttpSession sesija = request.getSession();
        String sifra = request.getParameter("hidden_id"),
                ime = request.getParameter("hidden_ime"),
                vrsta = request.getParameter("hidden_vrsta"),
                opis = request.getParameter("hidden_opis"),
                cena = request.getParameter("hidden_cena");
        p.setSifra(sifra);
        p.setIme(ime);
        p.setCena(cena);
        p.setVrsta(vrsta);
        p.setOpis(opis);
        if(sesija.getAttribute("cart") == null){
            ArrayList<Item> cart = new ArrayList<>();
            Item i = new Item(p, 1);
            cart.add(i);
            sesija.setAttribute("cart", cart);
        }
        else {
            ArrayList<Item> cart = (ArrayList<Item>)sesija.getAttribute("cart");
            int index = isExisting(request.getParameter("hidden_id"), cart);
            if(index == -1){
                Item i = new Item(p, 1);
                cart.add(i);
            }
            else{
                int kolicina = cart.get(index).getKolicina()+1;
                cart.get(index).setKolicina(kolicina);
            }
            sesija.setAttribute("cart", cart);
        }
       if(vrsta.equalsIgnoreCase("Slana Hrana")){
           response.sendRedirect("slaniproizvodi.jsp");
       }
       else if(vrsta.equalsIgnoreCase("Slatka Hrana")){
           response.sendRedirect("slatkiproizvodi.jsp");
       }
    }
    
    
    private int isExisting(String id, ArrayList<Item> cart){
        for(int i=0; i<cart.size(); i++)
        {
            if(cart.get(i).getProzivod().getSifra().equalsIgnoreCase(id)){
                return i;
            }
        }
        return -1;
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding(response.getCharacterEncoding());
        HttpSession sesija = request.getSession();
        ArrayList<Item> cart = (ArrayList<Item>)sesija.getAttribute("cart");
        String action = request.getParameter("action"),
                sifra = request.getParameter("sifra");
        if(action.equalsIgnoreCase("remove"))
        {
            for(int i=0; i<cart.size(); i++){
                if(cart.get(i).getProzivod().getSifra().equalsIgnoreCase(sifra))
                {
                    cart.remove(i);
                }
            }
            sesija.setAttribute("cart", cart);
            request.getRequestDispatcher("korpa.jsp").forward(request, response);
        }
        
        
    }
    
    
}
