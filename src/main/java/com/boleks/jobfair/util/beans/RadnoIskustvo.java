
package com.boleks.jobfair.util.beans;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class RadnoIskustvo {
    
    private int idRI;
    private int idCV;
    private Date datumOd;
    private Date datumDo;
    private String pozicija;
    private String kompanija;
    private String grad;
    private String zemlja;
    private String opisZaduzenja;
    
    
    //Konstruktori
    public RadnoIskustvo() {
    }

    public RadnoIskustvo(int idRI, int idCV, Date datumOd, Date datumDo, String pozicija, String kompanija, String grad, String zemlja, String opisZaduzenja) {
        this.idRI = idRI;
        this.idCV = idCV;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.pozicija = pozicija;
        this.kompanija = kompanija;
        this.grad = grad;
        this.zemlja = zemlja;
        this.opisZaduzenja = opisZaduzenja;
    }
    
    public RadnoIskustvo(ResultSet rs) throws SQLException {
        this.idRI = rs.getInt("idRI");
        this.idCV = rs.getInt("idCV");
        this.datumOd = rs.getDate("datumOd");
        this.datumDo = rs.getDate("datumDo");
        this.pozicija = rs.getString("pozicija");
        this.kompanija = rs.getString("kompanija");
        this.grad = rs.getString("grad");
        this.zemlja = rs.getString("zemlja");
        this.opisZaduzenja = rs.getString("opisZaduzenja");
    }
    
    
    //Geteri i seteri
    public int getIdRI() {
        return idRI;
    }

    public void setIdRI(int idRI) {
        this.idRI = idRI;
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

    public String getPozicija() {
        return pozicija;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public String getKompanija() {
        return kompanija;
    }

    public void setKompanija(String kompanija) {
        this.kompanija = kompanija;
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

    public String getOpisZaduzenja() {
        return opisZaduzenja;
    }

    public void setOpisZaduzenja(String opisZaduzenja) {
        this.opisZaduzenja = opisZaduzenja;
    }
    
    
    
}
