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
public class Item {
    private Proizvod prozivod;
    private int kolicina;
    
    public Item() {}

    public Item(Proizvod prozivod, int kolicina) {
        this.prozivod = prozivod;
        this.kolicina = kolicina;
    }

    public Proizvod getProzivod() {
        return prozivod;
    }

    public void setProzivod(Proizvod prozivod) {
        this.prozivod = prozivod;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    
    
}
