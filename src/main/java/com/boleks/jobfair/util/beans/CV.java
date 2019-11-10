
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CV {
    
    private int idCV;
    private int idKorisnik;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private String grad;
    private String ostaleVestine;
    private List<Obrazovanje> obrazivanje;
    private List<RadnoIskustvo> radnoIskustvo;
    private List<KompjuterskeVestine> kompjuterskeVestine;
    private List<Jezik> jezik;
    
    
    //Konstruktori
    public CV() {
    }

    public CV(int idCV, int idKorisnik, String ime, String prezime, String email, String telefon, String grad, String ostaleVestine, List<Obrazovanje> obrazivanje, List<RadnoIskustvo> radnoIskustvo, List<KompjuterskeVestine> kompjuterskeVestine, List<Jezik> jezik) {
        this.idCV = idCV;
        this.idKorisnik = idKorisnik;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.grad = grad;
        this.ostaleVestine = ostaleVestine;
        this.obrazivanje = obrazivanje;
        this.radnoIskustvo = radnoIskustvo;
        this.kompjuterskeVestine = kompjuterskeVestine;
        this.jezik = jezik;
    }
    
    //Konstruktor ResultSet bez lista (Obrazovanje, RadnoIskustbo, Kompjuterske Vestine, Jezik)
    public CV(ResultSet rs) throws SQLException {
        this.idCV = rs.getInt("idCV");
        this.idKorisnik = rs.getInt("idKorisnik");
        this.ime = rs.getString("ime");
        this.prezime = rs.getString("prezime");
        this.email = rs.getString("email");
        this.telefon = rs.getString("telefon");
        this.grad = rs.getString("grad");
        this.ostaleVestine = rs.getString("ostaleVestine");
        /*
        
        this.obrazivanje = obrazivanje;
        this.radnoIskustvo = radnoIskustvo;
        this.kompjuterskeVestine = kompjuterskeVestine;
        this.jezik = jezik;
        */
    }
    
    

    
    //Geteri i Seteri
    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(int idKorisnik) {
        this.idKorisnik = idKorisnik;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getOstaleVestine() {
        return ostaleVestine;
    }

    public void setOstaleVestine(String ostaleVestine) {
        this.ostaleVestine = ostaleVestine;
    }

    public List<Obrazovanje> getObrazivanje() {
        return obrazivanje;
    }

    public void setObrazivanje(List<Obrazovanje> obrazivanje) {
        this.obrazivanje = obrazivanje;
    }

    public List<RadnoIskustvo> getRadnoIskustvo() {
        return radnoIskustvo;
    }

    public void setRadnoIskustvo(List<RadnoIskustvo> radnoIskustvo) {
        this.radnoIskustvo = radnoIskustvo;
    }

    public List<KompjuterskeVestine> getKompjuterskeVestine() {
        return kompjuterskeVestine;
    }

    public void setKompjuterskeVestine(List<KompjuterskeVestine> kompjuterskeVestine) {
        this.kompjuterskeVestine = kompjuterskeVestine;
    }

    public List<Jezik> getJezik() {
        return jezik;
    }

    public void setJezik(List<Jezik> jezik) {
        this.jezik = jezik;
    }
    
    
    
    
    
    
    
    
    
    
    
}
