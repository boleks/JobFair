package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Konkurs;
import com.boleks.jobfair.util.beans.Student;
import com.boleks.jobfair.util.dao.KonkursDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named(value = "controllerKompanijaKonkurs")
@SessionScoped
public class ControllerKompanijaKonkurs implements Serializable {

    private Integer idKorisnik;
    private Konkurs kompanijaKonkurs = new Konkurs();
    private List<Student> rangLista = new ArrayList<>();
    private Part konkursPrilog;

    public Konkurs getKompanijaKonkurs() {
        return kompanijaKonkurs;
    }

    public void setKompanijaKonkurs(Konkurs kompanijaKonkurs) {
        this.kompanijaKonkurs = kompanijaKonkurs;
    }

    public List<Student> getRangLista() {
        return rangLista;
    }

    public void setRangLista(List<Student> rangLista) {
        this.rangLista = rangLista;
    }

    public void sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        idKorisnik = (int) session.getAttribute("KorisnikID");
    }

    public Integer getIdKorisnik() {
        return idKorisnik;
    }

    public void setIdKorisnik(Integer idKorisnik) {
        this.idKorisnik = idKorisnik;
    }

    public Part getKonkursPrilog() {
        return konkursPrilog;
    }

    public void setKonkursPrilog(Part konkursPrilog) {
        this.konkursPrilog = konkursPrilog;
    }

    public String kreirajKonkurs() throws SQLException {
        kompanijaKonkurs.setIdKompanija(idKorisnik);
        if (konkursPrilog != null) {

            if (konkursPrilog.getSize() > 1024000) {
                String greska = "Maksimalna dozvoljen velicina fajla je 1MB.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-kreirajKonkurs";
            }

            if (konkursPrilog.getContentType().equals("application/pdf")) {
                String[] ekstenzija = konkursPrilog.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                String imeFila = kompanijaKonkurs.getIdKompanija() + "-" + kompanijaKonkurs.getNazivPozicija().trim() + "-" + LocalDate.now().toString() + "." + ekstenzija[ekstenzija.length - 1];
                boolean upload = new FileUpload().saveFilePDF(konkursPrilog, imeFila);
                if (upload) {
                    kompanijaKonkurs.setPrilog(imeFila);
                }
            } else {
                String greska = "Dozvoljen je samo .pdf format priloga.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-kreirajKonkurs";
            }
        }else{
            kompanijaKonkurs.setPrilog("");
        }

        if (KonkursDAO.napraviKonkurs(kompanijaKonkurs) == 0) {
            String greska = "Doslo je do greske. Konkurs nije kreiran.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/kompanija/kompanija-kreirajKonkurs";
        }
        kompanijaKonkurs = new Konkurs();
        return "/index-kompanija?faces-redirect=true";

    }

    public ControllerKompanijaKonkurs() {
    }

}
