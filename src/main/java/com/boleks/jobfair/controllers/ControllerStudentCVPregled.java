package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.*;
import com.boleks.jobfair.util.dao.StudentCvDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;

@Named(value = "controllerStudentCVPregled")
@SessionScoped
public class ControllerStudentCVPregled implements Serializable {

    private CV cvKorisnika;
    private Obrazovanje obrazovanjeIzmena;
    private RadnoIskustvo radnoIskustvoIzmena;
    private Jezik jezikIzmena;
    private KompjuterskeVestine kompjuterskeVestineIzmena;

    public CV getCvKorisnika() {
        return cvKorisnika;
    }

    public void setCvKorisnika(CV cvKorisnika) {
        this.cvKorisnika = cvKorisnika;
    }

    public Obrazovanje getObrazovanjeIzmena() {
        return obrazovanjeIzmena;
    }

    public void setObrazovanjeIzmena(Obrazovanje obrazovanjeIzmena) {
        this.obrazovanjeIzmena = obrazovanjeIzmena;
    }

    public RadnoIskustvo getRadnoIskustvoIzmena() {
        return radnoIskustvoIzmena;
    }

    public void setRadnoIskustvoIzmena(RadnoIskustvo radnoIskustvoIzmena) {
        this.radnoIskustvoIzmena = radnoIskustvoIzmena;
    }

    public Jezik getJezikIzmena() {
        return jezikIzmena;
    }

    public void setJezikIzmena(Jezik jezikIzmena) {
        this.jezikIzmena = jezikIzmena;
    }

    public KompjuterskeVestine getKompjuterskeVestineIzmena() {
        return kompjuterskeVestineIzmena;
    }

    public void setKompjuterskeVestineIzmena(KompjuterskeVestine kompjuterskeVestineIzmena) {
        this.kompjuterskeVestineIzmena = kompjuterskeVestineIzmena;
    }

    public boolean korisnikImaCV() throws SQLException {
        int idKorisnika = sesijaKorisnikID();
        if (StudentCvDAO.korisnikImaCV(idKorisnika)) {
            cvKorisnika = StudentCvDAO.dohvatiCV(idKorisnika);
            return true;
        }
        return false;
    }

    public int sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (int) session.getAttribute("KorisnikID");
    }

    //Izmena podataka
    public String cvIzmenaOsnovnihPodataka() throws SQLException {
        if (StudentCvDAO.izmeniOsnovnePodatke(cvKorisnika)) {
            return "/student/studentCV-pregled?faces-redirect=true";
        }
        String greska = "Podaci nisu uspesno sniljeni. Proverite da li ste ispravno uneli podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniOsnovenePodatke";
    }

    public void obrisiObrazovanje(Obrazovanje ob) throws SQLException {
        if (StudentCvDAO.obrisiObrazovanjeIzListe(ob)) {
            cvKorisnika.getObrazivanje().remove(ob);
        }
    }

    public void obrisiRadnoIskustvo(RadnoIskustvo ri) throws SQLException {
        if (StudentCvDAO.obrisiRadnoIskustvoIzListe(ri)) {
            cvKorisnika.getRadnoIskustvo().remove(ri);
        }
    }

    public void obrisiJezik(Jezik jezik) throws SQLException {
        if (StudentCvDAO.obrisiJezikIzListe(jezik)) {
            cvKorisnika.getJezik().remove(jezik);
        }
    }

    public void obrisiKompjuterskuVestinu(KompjuterskeVestine kv) throws SQLException {
        if (StudentCvDAO.obrisiKompjuterskuVestinuIzListe(kv)) {
            cvKorisnika.getKompjuterskeVestine().remove(kv);
        }
    }

    public String izmeniObrazovanjeLink(Obrazovanje ob) {
        obrazovanjeIzmena = ob;
        return "/student/studentCV-izmeniObrazovanje?faces-redirect=true";
    }

    public String izmeniObrazovanje() throws SQLException {
        if (StudentCvDAO.izmeniObrazovanjeIzListe(obrazovanjeIzmena)) {
            for (int i = 0; i < cvKorisnika.getObrazivanje().size(); i++) {
                if (cvKorisnika.getObrazivanje().get(i).getIdObrazovanje() == obrazovanjeIzmena.getIdObrazovanje()) {
                    cvKorisnika.getObrazivanje().set(i, obrazovanjeIzmena);
                }
            }
            obrazovanjeIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }

        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniObrazovanje?faces-redirect=true";
    }

    public String izmeniRadnoIskustvoLink(RadnoIskustvo ri) {
        radnoIskustvoIzmena = ri;
        return "/student/studentCV-izmeniRadnoIskustvo?faces-redirect=true";
    }

    public String izmeniRadnoIskustvo() throws SQLException {
        if (StudentCvDAO.izmeniRadnoIskustvoIzListe(radnoIskustvoIzmena)) {
            for (int i = 0; i < cvKorisnika.getRadnoIskustvo().size(); i++) {
                if (cvKorisnika.getRadnoIskustvo().get(i).getIdRI() == radnoIskustvoIzmena.getIdRI()) {
                    cvKorisnika.getRadnoIskustvo().set(i, radnoIskustvoIzmena);
                }
            }
            radnoIskustvoIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }

        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniRadnoIskustvo?faces-redirect=true";
    }

    public String izmeniJezikLink(Jezik jezik) {
        jezikIzmena = jezik;
        return "/student/studentCV-izmeniJezik?faces-redirect=true";
    }

    public String izmeniJezik() throws SQLException {
        if (StudentCvDAO.izmeniJezikIzListe(jezikIzmena)) {
            for (int i = 0; i < cvKorisnika.getJezik().size(); i++) {
                if (cvKorisnika.getJezik().get(i).getIdJezik() == jezikIzmena.getIdJezik()) {
                    cvKorisnika.getJezik().set(i, jezikIzmena);
                }
            }
            jezikIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }

        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniJezik?faces-redirect=true";
    }

    public String izmeniKVLink(KompjuterskeVestine kv) {
        kompjuterskeVestineIzmena = kv;
        return "/student/studentCV-izmeniKompjuterskuVestinu?faces-redirect=true";
    }

    public String izmeniKV() throws SQLException {
        if (StudentCvDAO.izmeniKompjuterskuVestinuIzListe(kompjuterskeVestineIzmena)) {
            for (int i = 0; i < cvKorisnika.getKompjuterskeVestine().size(); i++) {
                if (cvKorisnika.getKompjuterskeVestine().get(i).getIdKV() == kompjuterskeVestineIzmena.getIdKV()) {
                    cvKorisnika.getKompjuterskeVestine().set(i, kompjuterskeVestineIzmena);
                }
            }
            kompjuterskeVestineIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }

        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniKompjuterskuVestinu?faces-redirect=true";
    }

    public String dodajObrazovanjeLink() {
        obrazovanjeIzmena = new Obrazovanje();
        obrazovanjeIzmena.setIdCV(cvKorisnika.getIdCV());
        return "/student/studentCV-dodajObrazovanjePregled?faces-redirect=true";
    }

    public String dodajObrazovanje() throws SQLException {
        int id = StudentCvDAO.dodajObrazovanjeUListe(obrazovanjeIzmena);
        if (id > 0) {
            obrazovanjeIzmena.setIdObrazovanje(id);
            cvKorisnika.getObrazivanje().add(obrazovanjeIzmena);
            obrazovanjeIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }
        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-dodajObrazovanjePregled?faces-redirect=true";
    }

    public String dodajRadnoIskustvoLink() {
        radnoIskustvoIzmena = new RadnoIskustvo();
        radnoIskustvoIzmena.setIdCV(cvKorisnika.getIdCV());
        return "/student/studentCV-dodajRadnoIskustvoPregled?faces-redirect=true";
    }

    public String dodajRadnoIskustvo() throws SQLException {
        int id = StudentCvDAO.dodajRadnoIskustvoUListe(radnoIskustvoIzmena);
        if (id > 0) {
            radnoIskustvoIzmena.setIdRI(id);
            cvKorisnika.getRadnoIskustvo().add(radnoIskustvoIzmena);
            radnoIskustvoIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }
        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-dodajRadnoIskustvoPregled?faces-redirect=true";
    }
    
        public String dodajJezikLink() {
        jezikIzmena = new Jezik();
        jezikIzmena.setIdCV(cvKorisnika.getIdCV());
        return "/student/studentCV-dodajJezikPregled?faces-redirect=true";
    }

    public String dodajJezik() throws SQLException {
        int id = StudentCvDAO.dodajJezikUListe(jezikIzmena);
        if (id > 0) {
            jezikIzmena.setIdJezik(id);
            cvKorisnika.getJezik().add(jezikIzmena);
            jezikIzmena = null;
            return "studentCV-pregled?faces-redirect=true";
        }
        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-dodajJezikPregled?faces-redirect=true";
    }
    
    public String dodajKompjuterskuVestinuLink() {
        kompjuterskeVestineIzmena = new KompjuterskeVestine();
        kompjuterskeVestineIzmena.setIdCV(cvKorisnika.getIdCV());
        return "/student/studentCV-dodajKompjuterskuVestinuPregled?faces-redirect=true";
    }

    public String dodajKompjuterskuVestinu() throws SQLException {
        int id = StudentCvDAO.dodajKompjuterskuVestinuUListe(kompjuterskeVestineIzmena);
        if (id > 0) {
            kompjuterskeVestineIzmena.setIdKV(id);
            cvKorisnika.getKompjuterskeVestine().add(kompjuterskeVestineIzmena);
            kompjuterskeVestineIzmena = null;
            return "/student/studentCV-pregled?faces-redirect=true";
        }
        String greska = "Doslo je do greske proverite da li ste uneli ispravno sve podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-dodajKompjuterskuVestinuPregled?faces-redirect=true";
    }
    
        public String izmeniOstaleVestine() throws SQLException {
         if (StudentCvDAO.izmeniOstaleVestine(cvKorisnika)) {
            return "/student/studentCV-pregled?faces-redirect=true";
        }
        String greska = "Podaci nisu uspesno sniljeni. Proverite da li ste ispravno uneli podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/studentCV-izmeniOstaleVestine?faces-redirect=true";
    }

    public ControllerStudentCVPregled() {
    }

}
