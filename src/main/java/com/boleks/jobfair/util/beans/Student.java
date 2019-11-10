package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Student extends Korisnik {
   
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String godinaStudija;
    private char diplomirao;

    public Student() {
        super();
    }

    public Student(String ime, String prezime, String telefon, String email, String godinaStudija, char diplomirao, int idKorisnik, String korisnickoIme, String lozinka, String slika, char tip) {
        super(idKorisnik, korisnickoIme, lozinka, slika, tip);
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.godinaStudija = godinaStudija;
        this.diplomirao = diplomirao;
    }
    
    public Student(ResultSet rs) throws SQLException{
                super(rs.getInt("idKorisnik"), rs.getString("korisnickoIme"), rs.getString("lozinka"), rs.getString("slika"), rs.getString("tip").charAt(0));
        this.ime = rs.getString("ime");
        this.prezime = rs.getString("prezime");
        this.telefon = rs.getString("telefon");
        this.email = rs.getString("email");
        this.godinaStudija = rs.getString("godinaStudija");
        this.diplomirao = rs.getString("diplomirao").charAt(0);
        
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGodinaStudija() {
        return godinaStudija;
    }

    public void setGodinaStudija(String godinaStudija) {
        this.godinaStudija = godinaStudija;
    }

    public char getDiplomirao() {
        return diplomirao;
    }

    public void setDiplomirao(char diplomirao) {
        this.diplomirao = diplomirao;
    }
    
    
    
    
    
    
}
