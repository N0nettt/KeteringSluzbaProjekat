/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author N0net
 */
public class Korisnik {
    private String username;
    private String password;
    private String email;
    private String poeni;
    private String role;
    
    public Korisnik () {}
    
    public Korisnik(String username, String password, String email, String poeni, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.poeni = poeni;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPoeni() {
        return poeni;
    }

    public void setPoeni(String poeni) {
        this.poeni = poeni;
    }
    
    
    
}
