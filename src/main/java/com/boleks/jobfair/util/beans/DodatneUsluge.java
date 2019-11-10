package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DodatneUsluge {

    private int idDodatak;
    private int idSajam;
    private String naziv;
    private String opis;
    private String cena;

    public DodatneUsluge() {
    }

    public DodatneUsluge(int idDodatak, int idSajam, String naziv, String opis, String cena) {
        this.idDodatak = idDodatak;
        this.idSajam = idSajam;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public DodatneUsluge(ResultSet rs) throws SQLException {
        this.idDodatak = rs.getInt("idDodatak");
        this.idSajam = rs.getInt("idSajam");
        this.naziv = rs.getString("naziv");
        this.opis = rs.getString("opis");
        this.cena = rs.getString("cena");

    }

    public int getIdDodatak() {
        return idDodatak;
    }

    public void setIdDodatak(int idDodatak) {
        this.idDodatak = idDodatak;
    }

    public int getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(int idSajam) {
        this.idSajam = idSajam;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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
