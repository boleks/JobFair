package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Satnica {
     private int idSatnica;
     private int idSajam;
     private Date datum;
     private String vreme;
     private String tip;
     private String mestoOdrzavanja;
     private String status;

    public Satnica() {
    }

    public Satnica(int idSatnica, int idSajam, Date datum, String vreme, String tip, String mestoOdrzavanja, String status) {
        this.idSatnica = idSatnica;
        this.idSajam = idSajam;
        this.datum = datum;
        this.vreme = vreme;
        this.tip = tip;
        this.mestoOdrzavanja = mestoOdrzavanja;
        this.status = status;
    }
    
    public Satnica(ResultSet rs)throws SQLException{
        this.idSatnica =rs.getInt("idSatnica");
        this.idSajam = rs.getInt("idSajam");
        this.datum = rs.getDate("datum");
        this.vreme = rs.getString("vreme");
        this.tip = rs.getString("tip");
        this.mestoOdrzavanja = rs.getString("mestoOdrzavanja");
        this.status=rs.getString("status");
   
    }
    
    //getteri  i setteri  

    public int getIdSatnica() {
        return idSatnica;
    }

    public void setIdSatnica(int idSatnica) {
        this.idSatnica = idSatnica;
    }

    public int getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(int idSajam) {
        this.idSajam = idSajam;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getMestoOdrzavanja() {
        return mestoOdrzavanja;
    }

    public void setMestoOdrzavanja(String mestoOdrzavanja) {
        this.mestoOdrzavanja = mestoOdrzavanja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
     
     
     
    
    
}
