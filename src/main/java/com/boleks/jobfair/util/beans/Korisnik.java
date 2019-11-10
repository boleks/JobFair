package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Korisnik {

    private int idKorisnik;
    private String korisnickoIme;
    private String lozinka;
    private String slika;
    private char tip;

    public Korisnik() {
    }

    public Korisnik(int idKorisnik, String korisnickoIme, String lozinka, String slika, char tip) {
        this.idKorisnik = idKorisnik;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.slika = slika;
        this.tip = tip;
    }

    public Korisnik(ResultSet rs) throws SQLException {
        this.idKorisnik = rs.getInt("idKorisnik");
        this.korisnickoIme = rs.getString("korisnickoIme");
        this.lozinka = rs.getString("lozinka");
        this.slika = rs.getString("slika");
        this.tip = rs.getString("tip").charAt(0);
    }

    public char getTip() {
        return tip;
    }

    public void setTip(char tip) {
        this.tip = tip;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

}
