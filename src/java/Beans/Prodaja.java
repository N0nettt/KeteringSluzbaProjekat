/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.sql.Date;

/**
 *
 * @author N0net
 */
public class Prodaja {
    
    private int porudzbinaID, cena;
    private String username;
    private Date vreme;

    public Prodaja(){}
    
    public Prodaja(int porudzbinaID, String username, int cena, Date vreme) {
        this.porudzbinaID = porudzbinaID;
        this.username = username;
        this.cena = cena;
        this.vreme = vreme;
    }

    public int getPorudzbinaID() {
        return porudzbinaID;
    }

    public void setPorudzbinaID(int porudzbinaID) {
        this.porudzbinaID = porudzbinaID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public Date getVreme() {
        return vreme;
    }

    public void setVreme(Date vreme) {
        this.vreme = vreme;
    }
    
    
}
