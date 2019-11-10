package com.boleks.jobfair.util.beans;


public class Administrator extends Korisnik {
    public String ime;
    public String prezime;
    public String telefon;
    public String email;

    public Administrator() {
        super();
    }

    public Administrator(int idKorisnik, String korisnickoIme,String lozinka,String slika,char tip,String ime, String prezime, String telefon, String email) {
        super(idKorisnik, korisnickoIme, lozinka, slika, tip);
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
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


    
    
    
}
