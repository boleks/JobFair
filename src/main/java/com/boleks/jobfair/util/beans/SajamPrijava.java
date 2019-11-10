
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SajamPrijava {

    private Integer idPrijava;
    private Integer idSajam;
    private Integer idPaket;
    private Integer idKompanija;
    private String status;
    private String napomena;

    public SajamPrijava() {
    }

    public SajamPrijava(Integer idPrijava, Integer idSajam, Integer idPaket, Integer idKompanija, String status, String napomena) {
        this.idPrijava = idPrijava;
        this.idSajam = idSajam;
        this.idPaket = idPaket;
        this.idKompanija = idKompanija;
        this.status = status;
        this.napomena = napomena;
    }
    
    public SajamPrijava(ResultSet rs) throws SQLException {
        this.idPrijava = rs.getInt("idPrijava");
        this.idSajam = rs.getInt("idSajam");
        this.idPaket = rs.getInt("idPaket");
        this.idKompanija = rs.getInt("idKompanija");
        this.status = rs.getString("status");
        this.napomena = rs.getString("napomena");
    }

    public Integer getIdPrijava() {
        return idPrijava;
    }

    public void setIdPrijava(Integer idPrijava) {
        this.idPrijava = idPrijava;
    }

    public Integer getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(Integer idSajam) {
        this.idSajam = idSajam;
    }

    public Integer getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(Integer idPaket) {
        this.idPaket = idPaket;
    }

    public Integer getIdKompanija() {
        return idKompanija;
    }

    public void setIdKompanija(Integer idKompanija) {
        this.idKompanija = idKompanija;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }        
}
