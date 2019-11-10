
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class Obrazovanje {
    
    private int idObrazovanje;
    private int idCV;
    private Date datumOd;
    private Date datumDo;
    private String nazivSkole;
    private String smer;
    private String grad;
    private String zemlja;

    public Obrazovanje() {
    }

    public Obrazovanje(int idObrazovanje, int idCV, Date datumOd, Date datumDo, String nazivSkole, String smer, String grad, String zemlja) {
        this.idObrazovanje = idObrazovanje;
        this.idCV = idCV;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.nazivSkole = nazivSkole;
        this.smer = smer;
        this.grad = grad;
        this.zemlja = zemlja;
    }
    
       public Obrazovanje(ResultSet rs) throws SQLException {
        this.idObrazovanje = rs.getInt("idObrazovanje");
        this.idCV = rs.getInt("idCV");
        this.datumOd = rs.getDate("datumOd");
        this.datumDo = rs.getDate("datumDo");
        this.nazivSkole = rs.getString("nazivSkole");
        this.smer = rs.getString("smer");
        this.grad = rs.getString("grad");
        this.zemlja = rs.getString("zemlja");
    }

    public int getIdObrazovanje() {
        return idObrazovanje;
    }

    public void setIdObrazovanje(int idObrazovanje) {
        this.idObrazovanje = idObrazovanje;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getNazivSkole() {
        return nazivSkole;
    }

    public void setNazivSkole(String nazivSkole) {
        this.nazivSkole = nazivSkole;
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getZemlja() {
        return zemlja;
    }

    public void setZemlja(String zemlja) {
        this.zemlja = zemlja;
    }
    
    
    
        
}
