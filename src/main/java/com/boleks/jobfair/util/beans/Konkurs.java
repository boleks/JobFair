
package com.boleks.jobfair.util.beans;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Konkurs {
    private Integer idKonkurs;
    private Integer idKompanija;
    private String nazivPozicija;
    private String tekstKonkursa;
    private Date rokZaPrijavu;
    private String prilog;
    private char tip;

    public Konkurs() {
    }

    public Konkurs(Integer idKonkurs, Integer idKompanija, String nazivPozicija, String tekstKonkursa, Date rokZaPrijavu, String prilog, char tip) {
        this.idKonkurs = idKonkurs;
        this.idKompanija = idKompanija;
        this.nazivPozicija = nazivPozicija;
        this.tekstKonkursa = tekstKonkursa;
        this.rokZaPrijavu = rokZaPrijavu;
        this.prilog = prilog;
        this.tip = tip;
    }
    
        public Konkurs(ResultSet rs) throws SQLException {
        this.idKonkurs = rs.getInt("idKonkurs");
        this.idKompanija = rs.getInt("idKompanija");
        this.nazivPozicija = rs.getString("nazivPozicija");
        this.tekstKonkursa = rs.getString("tekstKonkursa");
        this.rokZaPrijavu = rs.getDate("rokZaPrijavu");
        this.prilog = rs.getString("prilog");
        this.tip = rs.getString("tip").charAt(0);
    }
    
    
    public Integer getIdKonkurs() {
        return idKonkurs;
    }

    public void setIdKonkurs(Integer idKonkurs) {
        this.idKonkurs = idKonkurs;
    }

    public Integer getIdKompanija() {
        return idKompanija;
    }

    public void setIdKompanija(Integer idKompanija) {
        this.idKompanija = idKompanija;
    }

    public String getNazivPozicija() {
        return nazivPozicija;
    }

    public void setNazivPozicija(String nazivPozicija) {
        this.nazivPozicija = nazivPozicija;
    }

    public String getTekstKonkursa() {
        return tekstKonkursa;
    }

    public void setTekstKonkursa(String tekstKonkursa) {
        this.tekstKonkursa = tekstKonkursa;
    }

    public Date getRokZaPrijavu() {
        return rokZaPrijavu;
    }

    public void setRokZaPrijavu(Date rokZaPrijavu) {
        this.rokZaPrijavu = rokZaPrijavu;
    }

    public String getPrilog() {
        return prilog;
    }

    public void setPrilog(String prilog) {
        this.prilog = prilog;
    }

    public char getTip() {
        return tip;
    }

    public void setTip(char tip) {
        this.tip = tip;
    }
                    
    
}
