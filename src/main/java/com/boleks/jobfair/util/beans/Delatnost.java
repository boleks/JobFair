/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boleks.jobfair.util.beans;

/**
 *
 * @author Obuka
 */
public class Delatnost {
    
    private int idDelatnost;
    private String naziv;

    public Delatnost(int idDelatnost, String naziv) {
        this.idDelatnost = idDelatnost;
        this.naziv = naziv;
    }

    public int getIdDelatnost() {
        return idDelatnost;
    }

    public void setIdDelatnost(int idDelatnost) {
        this.idDelatnost = idDelatnost;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    
    
}
