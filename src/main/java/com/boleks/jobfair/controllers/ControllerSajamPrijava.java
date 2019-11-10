package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.DodatneUsluge;
import com.boleks.jobfair.util.beans.Paket;
import com.boleks.jobfair.util.beans.Sajam;
import com.boleks.jobfair.util.beans.SajamPrijava;
import com.boleks.jobfair.util.dao.SajamDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named(value = "controllerSajamPrijava")
@SessionScoped
public class ControllerSajamPrijava implements Serializable {

    private SajamPrijava sajamPrijava = new SajamPrijava();
    private Sajam sajamInfo;
    private List<Sajam> sajmovi = SajamDAO.dohvatiAktivneSajmove(LocalDate.now());
    private List<Paket> paketi;
    private List<DodatneUsluge> dodatneUsluge;
    private int[] selektovaneDodatneUsluge;
    private Paket izabraniPaket;
    private List<DodatneUsluge> izabraneDodatneUsluge;
    private int ukupnaCenaZaPrijavu;
    private List<SajamPrijava> prijaveKompanija;

    public SajamPrijava getSajamPrijava() {
        return sajamPrijava;
    }

    public void setSajamPrijava(SajamPrijava sajamPrijava) {
        this.sajamPrijava = sajamPrijava;
    }

    public List<Sajam> getSajmovi() {
        return sajmovi;
    }

    public void setSajmovi(List<Sajam> sajmovi) {
        this.sajmovi = sajmovi;
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public Sajam getSajamInfo() {
        return sajamInfo;
    }

    public void setSajamInfo(Sajam sajamInfo) {
        this.sajamInfo = sajamInfo;
    }

    public Paket getIzabraniPaket() {
        return izabraniPaket;
    }

    public void setIzabraniPaket(Paket izabraniPaket) {
        this.izabraniPaket = izabraniPaket;
    }

    public List<DodatneUsluge> getIzabraneDodatneUsluge() {
        return izabraneDodatneUsluge;
    }

    public void setIzabraneDodatneUsluge(List<DodatneUsluge> izabraneDodatneUsluge) {
        this.izabraneDodatneUsluge = izabraneDodatneUsluge;
    }

    public int getUkupnaCenaZaPrijavu() {
        return ukupnaCenaZaPrijavu;
    }

    public void setUkupnaCenaZaPrijavu(int ukupnaCenaZaPrijavu) {
        this.ukupnaCenaZaPrijavu = ukupnaCenaZaPrijavu;
    }

    public List<DodatneUsluge> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatneUsluge> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public int[] getSelektovaneDodatneUsluge() {
        return selektovaneDodatneUsluge;
    }

    public void setSelektovaneDodatneUsluge(int[] selektovaneDodatneUsluge) {
        this.selektovaneDodatneUsluge = selektovaneDodatneUsluge;
    }

    public List<SajamPrijava> getPrijaveKompanija() {
        return prijaveKompanija;
    }

    public void setPrijaveKompanija(List<SajamPrijava> prijaveKompanija) {
        this.prijaveKompanija = prijaveKompanija;
    }
    
    

        public int sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (int) session.getAttribute("KorisnikID");

    }

    public String dodajPrijavuNaSajam() throws SQLException {
        
        sajamPrijava.setStatus("Cekanje");
        sajamPrijava.setNapomena(" ");
        sajamPrijava.setIdPrijava(SajamDAO.snimiPrijavuNaSajam(sajamPrijava));

        for (DodatneUsluge du : izabraneDodatneUsluge) {
            SajamDAO.snimiDodatnuUsluguNaPrijavu(sajamPrijava.getIdPrijava(), du.getIdDodatak());
        }
        return "/index-kompanija?faces-redirect=true";
    }

    public void osveziPodatkeZaSajam(ValueChangeEvent e) throws SQLException {
        Integer selektovanSajam = (Integer) e.getNewValue();
        if (selektovanSajam == null) {
            paketi = new ArrayList<>();
            dodatneUsluge = new ArrayList<>();
            return;
        }
        sajamInfo = sajmovi.get(selektovanSajam - 1);
        paketi = SajamDAO.dohvatiPaketeZaSajam(selektovanSajam);
        dodatneUsluge = SajamDAO.dohvatiDodatneUslugeZaSajam(selektovanSajam);
    }

    public String pregledPrijave() throws SQLException {

        sajamPrijava.setIdKompanija(sesijaKorisnikID());
        izabraniPaket = SajamDAO.dohvatiPaketZaPrijavu(sajamPrijava.getIdPaket());
        ukupnaCenaZaPrijavu = izabraniPaket.getCena();
        izabraneDodatneUsluge = new ArrayList<>();
        for (int selektovanaDodatnaUsluga : selektovaneDodatneUsluge) {
            DodatneUsluge du = SajamDAO.dohvatiDodatnuUslugu(selektovanaDodatnaUsluga);
            ukupnaCenaZaPrijavu += Integer.parseInt(du.getCena());
            izabraneDodatneUsluge.add(du);
        }

        return "kompanija-pregledPrijave?faces-redirect=true";
    }   
    
    public boolean paketPopunjeProvera(Paket p) throws SQLException{
        if(p.getMaxBroj()>0 && p.getMaxBroj() <= SajamDAO.brojOdobrenihPrijava(p.getIdPaket())){
            return true;
        }
        return false;
    }
    
    public boolean kompanijaImaPrijavuZaSajam() throws SQLException{
        if(SajamDAO.kompanijaImaPrijavu(sesijaKorisnikID())){
            return true;
        }
        return false;
    }
    
    public void dohvatiSvePrijaveKompanijeNaSajam() throws SQLException{
        prijaveKompanija = SajamDAO.dohvatiSvePrijaveKompanije(sesijaKorisnikID());
    }
    
    public String dohvatiNazivSajma(int idSajam) throws SQLException{
        return SajamDAO.dohvatiNazivSajma(idSajam);
    }
        
    public ControllerSajamPrijava() throws SQLException {
    }

}
