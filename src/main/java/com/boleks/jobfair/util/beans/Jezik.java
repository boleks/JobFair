
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Jezik {
    
    private int idJezik;
    private int idCV;
    private String naziv;
    private String nivo;
    
    
    //Konstruktori
    public Jezik() {
    }

    public Jezik(int idJezik, int idCV, String naziv, String nivo) {
        this.idJezik = idJezik;
        this.idCV = idCV;
        this.naziv = naziv;
        this.nivo = nivo;
    }
    
    public Jezik(ResultSet rs) throws SQLException {
        this.idJezik = rs.getInt("idJezik");
        this.idCV = rs.getInt("idCV");
        this.naziv = rs.getString("naziv");
        this.nivo = rs.getString("nivo");
    }

    //Geteri i seteri
    public int getIdJezik() {
        return idJezik;
    }

    public void setIdJezik(int idJezik) {
        this.idJezik = idJezik;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }
    
    
    
}
