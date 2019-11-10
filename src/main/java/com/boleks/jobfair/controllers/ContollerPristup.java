package com.boleks.jobfair.controllers;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "contollerPristup")
@RequestScoped
public class ContollerPristup {

    private String tipKorisnika;

    public String getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(String tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public String proveraStudent() {
        tipKorisnika = "S";
        return validanKorisnik(tipKorisnika) ? null : "/prijava.xhtml";
    }

    public String proveraKompanija() {
        tipKorisnika = "K";
        return validanKorisnik(tipKorisnika) ? null : "/prijava.xhtml";
    }
    
    public String proveraAdministrator() {
        tipKorisnika = "A";
        return validanKorisnik(tipKorisnika) ? null : "/prijava.xhtml";
    }

    public boolean validanKorisnik(String tip) {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        if (session == null || session.getAttribute("TipKorisnika") == null || session.getAttribute("Logovan") == null) {
            return false;
        } else if (!session.getAttribute("TipKorisnika").toString().equals(tip) || !session.getAttribute("Logovan").toString().equals("logovan")) {
            return false;
        }
        return true;
    }

    public ContollerPristup() {
    }

}
