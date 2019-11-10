package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Kompanija extends Korisnik {
    public String naziv;
    public int grad;
    public String adresa;
    public String direktor;
    public Integer pib;
    public Integer brojZaposlenih;
    public String email;
    public String websajt;
    public int delatnost;
    public String uzaDelatnost;

    public Kompanija() {
        super();
    }

    public Kompanija(String naziv, int grad, String adresa, String direktor, int pib, int brojZaposlenih, String email, String websajt, int delatnost, String uzaDelatnost, int idKorisnik, String korisnickoIme, String lozinka, String slika, char tip) {
        super(idKorisnik, korisnickoIme, lozinka, slika, tip);
        this.naziv = naziv;
        this.grad = grad;
        this.adresa = adresa;
        this.direktor = direktor;
        this.pib = pib;
        this.brojZaposlenih = brojZaposlenih;
        this.email = email;
        this.websajt = websajt;
        this.delatnost = delatnost;
        this.uzaDelatnost = uzaDelatnost;
    }
    
        public Kompanija(ResultSet rs) throws SQLException {
        super(rs.getInt("idKorisnik"), rs.getString("korisnickoIme"), rs.getString("lozinka"), rs.getString("slika"), rs.getString("tip").charAt(0));
        this.naziv = rs.getString("naziv");
        this.grad = rs.getInt("grad");
        this.adresa = rs.getString("adresa");
        this.direktor = rs.getString("direktor");
        this.pib = rs.getInt("pib");
        this.brojZaposlenih = rs.getInt("brojZaposlenih");
        this.email = rs.getString("email");
        this.websajt = rs.getString("websajt");
        this.delatnost = rs.getInt("delatnost");
        this.uzaDelatnost = rs.getString("uzaDelatnost");
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGrad() {
        return grad;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }        

    public void setGrad(int grad) {
        this.grad = grad;
    }

    public String getDirektor() {
        return direktor;
    }

    public void setDirektor(String direktor) {
        this.direktor = direktor;
    }

    public Integer getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public Integer getBrojZaposlenih() {
        return brojZaposlenih;
    }

    public void setBrojZaposlenih(int brojZaposlenih) {
        this.brojZaposlenih = brojZaposlenih;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsajt() {
        return websajt;
    }

    public void setWebsajt(String websajt) {
        this.websajt = websajt;
    }

    public int getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(int delatnost) {
        this.delatnost = delatnost;
    }

    public String getUzaDelatnost() {
        return uzaDelatnost;
    }

    public void setUzaDelatnost(String uzaDelatnost) {
        this.uzaDelatnost = uzaDelatnost;
    }

    public void setPib(Integer pib) {
        this.pib = pib;
    }

    public void setBrojZaposlenih(Integer brojZaposlenih) {
        this.brojZaposlenih = brojZaposlenih;
    }
    
    
    
}
