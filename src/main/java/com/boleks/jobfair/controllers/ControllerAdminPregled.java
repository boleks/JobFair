package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Sajam;
import com.boleks.jobfair.util.beans.SajamPrijava;
import com.boleks.jobfair.util.beans.Satnica;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.SajamDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named(value = "controllerAdminPregled")
@SessionScoped
public class ControllerAdminPregled implements Serializable {

    private int idSajamSatnica;
    private int idSajam;
    private List<Sajam> sviSajmovi;
    private List<Satnica> satnicaSajam;
    private List<SajamPrijava> ucesniciSajam;
    private Satnica izmeniSatnicu;

    public int getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(int idSajam) {
        this.idSajam = idSajam;
    }

    public List<Satnica> getSatnicaSajam() {
        return satnicaSajam;
    }

    public void setSatnicaSajam(List<Satnica> satnicaSajam) {
        this.satnicaSajam = satnicaSajam;
    }

    public List<SajamPrijava> getUcesniciSajam() {
        return ucesniciSajam;
    }

    public void setUcesniciSajam(List<SajamPrijava> ucesniciSajam) {
        this.ucesniciSajam = ucesniciSajam;
    }

    public List<Sajam> getSviSajmovi() {
        return sviSajmovi;
    }

    public void setSviSajmovi(List<Sajam> sviSajmovi) {
        this.sviSajmovi = sviSajmovi;
    }

    public void dohvatiSajmove() throws SQLException {
        sviSajmovi = SajamDAO.dohvatiSajmove();
    }

    public Satnica getIzmeniSatnicu() {
        return izmeniSatnicu;
    }

    public void setIzmeniSatnicu(Satnica izmeniSatnicu) {
        this.izmeniSatnicu = izmeniSatnicu;
    }

    public int getIdSajamSatnica() {
        return idSajamSatnica;
    }

    public void setIdSajamSatnica(int idSajamSatnica) {
        this.idSajamSatnica = idSajamSatnica;
    }        

    public void osveziPodatkeZaSajam(ValueChangeEvent e) throws SQLException {
        idSajam = (Integer) e.getNewValue();
        if (idSajam != 0) {
            ucesniciSajam = SajamDAO.dohvatiSveOdobrenePrijave(idSajam);
        }else{
            ucesniciSajam = new ArrayList<>();
        }
    }

    public String dohvatiNazivPaketa(int idPaket) throws SQLException {
        return SajamDAO.dohvatiPaketZaPrijavu(idPaket).getNaziv();
    }

    public String dohvatiNazivKompanije(int idkompanija) throws SQLException {
        return KompanijaDAO.dohvatiNaziv(idkompanija);
    }
    
    public String izmeniSatnicuLink(Satnica s){
        izmeniSatnicu = s;
        return "/administrator/admin-izmeniSatnicu?faces-redirect=true";
    }
    
    public String izmeniSatnicu() throws SQLException{        
        SajamDAO.izmeniSatnicu(izmeniSatnicu);
        return "/administrator/admin-pregled-satnice?faces-redirect=true";
    }

    public ControllerAdminPregled() throws SQLException {
        this.sviSajmovi = SajamDAO.dohvatiSajmove();
    }
    
    public void osveziPodatkeZaSajamSatnica(ValueChangeEvent e) throws SQLException {
        idSajamSatnica = (Integer) e.getNewValue();
        if (idSajamSatnica != 0) {
            satnicaSajam = SajamDAO.dohvatiSatniceSajma(idSajamSatnica);
        }else{
            satnicaSajam = new ArrayList<>();
        }
    }

}
