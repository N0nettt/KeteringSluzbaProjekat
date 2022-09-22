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
public class Proizvod {
    private String sifra, ime, slika, vrsta, opis, cena;
    
    public Proizvod() {}

    public Proizvod(String sifra, String ime, String slika, String vrsta, String opis, String cena) {
        this.sifra = sifra;
        this.ime = ime;
        this.slika = slika;
        this.vrsta = vrsta;
        this.opis = opis;
        this.cena = cena;
    }
    
    

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getVrsta() {
        return vrsta;
    }

    public void setVrsta(String vrsta) {
        this.vrsta = vrsta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }
    
    
}
