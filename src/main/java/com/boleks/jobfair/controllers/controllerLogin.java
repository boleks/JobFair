package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.Enkripcija;
import com.boleks.jobfair.util.beans.Korisnik;
import com.boleks.jobfair.util.dao.KorisnikDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Named(value = "controllerLogin")
@SessionScoped
public class controllerLogin implements Serializable {

    private String korisnickoIme;
    private String lozinka;
    private boolean prijavljen = false;
    private String poruka = "Uneli ste pogresne podatke za logovanje. Pokusajte ponovo.";
    private Korisnik korisnik;

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

    public boolean isPrijavljen() {
        return prijavljen;
    }

    public void setPrijavljen(boolean prijavljen) {
        this.prijavljen = prijavljen;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String logovanjeKorisnika() throws SQLException, NoSuchAlgorithmException {

        this.korisnik = KorisnikDAO.dohvatiKorisnika(korisnickoIme, Enkripcija.enkripcijaLozinke(lozinka));

        if (korisnik != null) {

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("KorisnikID", korisnik.getIdKorisnik());
            session.setAttribute("TipKorisnika", korisnik.getTip());
            session.setAttribute("Logovan", "logovan");
        }

        if (korisnik.getTip() == 'S' && korisnik != null) {
            return "index-student?faces-redirect=true";
        } else if (korisnik.getTip() == 'K') {
            return "index-kompanija?faces-redirect=true";
        } else if (korisnik.getTip() == 'A') {
            return "index-administrator?faces-redirect=true";
        }
        String PogresnoLogovanje = "Uneli ste pogresne podatke za prijavu.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, PogresnoLogovanje, null));

        return "/prijava";
    }

    public String sesijaKorisnik() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session != null) {
            return (String) session.getAttribute("KorisnikID");
        } else {
            return null;
        }
    }

    public boolean sesijaTip(char tip) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session == null) {
            return false;
        } else if (session.getAttribute("TipKorisnika").toString().charAt(0) == tip) {
            return true;
        }
        return false;
    }

    public boolean sesijaLogovan() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session != null && session.getAttribute("Logovan") == "logovan") {
            return true;
        } else {
            return false;
        }
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "/index?faces-redirect=true";
    }

    public void studentPristup() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.isValidationFailed()) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect(externalContext.getRequestContextPath() + "/index?faces-redirect=true");
        }
    }

    public controllerLogin() {

    }

}
