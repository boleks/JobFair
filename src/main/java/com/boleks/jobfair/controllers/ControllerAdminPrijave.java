package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.*;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.SajamDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named(value = "controllerAdminPrijave")
@SessionScoped
public class ControllerAdminPrijave implements Serializable {

    private List<SajamPrijava> svePrijave;
    private int idSajam;
    private List<Sajam> sajmovi = SajamDAO.dohvatiAktivneSajmove(LocalDate.now());
    private List<Paket> paketi;
    private List<DodatneUsluge> dodatneUsluge;
    private SajamPrijava pregledPrijave;
    private Paket pregledPaket;
    private List<DodatneUsluge> pregledDodatneUsluge;
    private int pregledPrijaveUkupnaCena;
    private List<Satnica> rezervacijaPredavanja;
    private List<Satnica> rezervacijaRadionica;
    private List<Satnica> rezervacijaPrezentacije;  
    private List<Satnica> satnicaDostupneZaPredavanja;
    private List<Satnica> satnicaDostupneZaRadionice;
    private List<Satnica> satnicaDostupneZaPrezentacije;

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public List<DodatneUsluge> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatneUsluge> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public List<Sajam> getSajmovi() {
        return sajmovi;
    }

    public void setSajmovi(List<Sajam> sajmovi) {
        this.sajmovi = sajmovi;
    }

    public List<SajamPrijava> getSvePrijave() {
        return svePrijave;
    }

    public void setSvePrijave(List<SajamPrijava> svePrijave) throws SQLException {
        this.svePrijave = SajamDAO.dohvatiSvePrijave(1);
    }

    public int getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(int idSajam) {
        this.idSajam = idSajam;
    }

    public SajamPrijava getPregledPrijave() {
        return pregledPrijave;
    }

    public void setPregledPrijave(SajamPrijava pregledPrijave) {
        this.pregledPrijave = pregledPrijave;
    }

    public List<DodatneUsluge> getPregledDodatneUsluge() {
        return pregledDodatneUsluge;
    }

    public void setPregledDodatneUsluge(List<DodatneUsluge> pregledDodatneUsluge) {
        this.pregledDodatneUsluge = pregledDodatneUsluge;
    }

    public Paket getPregledPaket() {
        return pregledPaket;
    }

    public void setPregledPaket(Paket pregledPaket) {
        this.pregledPaket = pregledPaket;
    }

    public int getPregledPrijaveUkupnaCena() {
        return pregledPrijaveUkupnaCena;
    }

    public void setPregledPrijaveUkupnaCena(int pregledPrijaveUkupnaCena) {
        this.pregledPrijaveUkupnaCena = pregledPrijaveUkupnaCena;
    }

    public List<Satnica> getRezervacijaPredavanja() {
        return rezervacijaPredavanja;
    }

    public void setRezervacijaPredavanja(List<Satnica> rezervacijaPredavanja) {
        this.rezervacijaPredavanja = rezervacijaPredavanja;
    }

    public List<Satnica> getRezervacijaRadionica() {
        return rezervacijaRadionica;
    }

    public void setRezervacijaRadionica(List<Satnica> rezervacijaRadionica) {
        this.rezervacijaRadionica = rezervacijaRadionica;
    }

    public List<Satnica> getRezervacijaPrezentacije() {
        return rezervacijaPrezentacije;
    }

    public void setRezervacijaPrezentacije(List<Satnica> rezervacijaPrezentacije) {
        this.rezervacijaPrezentacije = rezervacijaPrezentacije;
    }

    public List<Satnica> getSatnicaDostupneZaPredavanja() {
        return satnicaDostupneZaPredavanja;
    }

    public void setSatnicaDostupneZaPredavanja(List<Satnica> satnicaDostupneZaPredavanja) {
        this.satnicaDostupneZaPredavanja = satnicaDostupneZaPredavanja;
    }

    public List<Satnica> getSatnicaDostupneZaRadionice() {
        return satnicaDostupneZaRadionice;
    }

    public void setSatnicaDostupneZaRadionice(List<Satnica> satnicaDostupneZaRadionice) {
        this.satnicaDostupneZaRadionice = satnicaDostupneZaRadionice;
    }

    public List<Satnica> getSatnicaDostupneZaPrezentacije() {
        return satnicaDostupneZaPrezentacije;
    }

    public void setSatnicaDostupneZaPrezentacije(List<Satnica> satnicaDostupneZaPrezentacije) {
        this.satnicaDostupneZaPrezentacije = satnicaDostupneZaPrezentacije;
    }

    //Metode 
    public String dohvatiSvePrijave() throws SQLException {

        svePrijave = SajamDAO.dohvatiSvePrijave(1);

        return "/administrator/admin-pregledPristiglihPrijava?faces-redirect=true";
    }

    public String pregledajPrijavu(SajamPrijava sp) throws SQLException {
        pregledPrijave = sp;
        pregledPaket = SajamDAO.dohvatiPaketZaPrijavu(pregledPrijave.getIdPaket());
        pregledDodatneUsluge = SajamDAO.dohvatiDodatneUslugePrijave(pregledPrijave.getIdPrijava());

        pregledPrijaveUkupnaCena = pregledPaket.getCena();

        for (DodatneUsluge du : pregledDodatneUsluge) {
            pregledPrijaveUkupnaCena += Integer.parseInt(du.getCena());
        }

        return "/administrator/admin-pregledPrijave?faces-redirect=true";
    }

    public boolean paketPopunjeProvera() throws SQLException {
        if (pregledPaket.getMaxBroj() > 0 && pregledPaket.getMaxBroj() <= SajamDAO.brojOdobrenihPrijava(pregledPaket.getIdPaket())) {
            return true;
        }
        return false;
    }

    public String odobriPrijavu() throws SQLException {

        rezervacijaPredavanja = new ArrayList<>();
        for (int i = 0; i < pregledPaket.getBrojPredavanja(); i++) {
            Satnica p = new Satnica();            
            rezervacijaPredavanja.add(p);
        }
        rezervacijaRadionica = new ArrayList<>();
        for (int i = 0; i < pregledPaket.getBrojRadionica(); i++) {
            Satnica r = new Satnica();            
            rezervacijaRadionica.add(r);
        }

        satnicaDostupneZaPredavanja = SajamDAO.dohvatiDostupneSatnice("Predavanje");
        satnicaDostupneZaRadionice = SajamDAO.dohvatiDostupneSatnice("Radionica");
        satnicaDostupneZaPrezentacije = new ArrayList<>();
        rezervacijaPrezentacije = new ArrayList<>();
        for (DodatneUsluge du : pregledDodatneUsluge) {
            if (du.getOpis().contains("Prezentacija")) {
                
                Satnica pr = new Satnica();             
                rezervacijaPrezentacije.add(pr);
                satnicaDostupneZaPrezentacije = SajamDAO.dohvatiDostupneSatnice("Prezentacija");
            };
        }

        if (!rezervacijaPredavanja.isEmpty() || !rezervacijaRadionica.isEmpty() || !rezervacijaPrezentacije.isEmpty()) {
            return "/administrator/admin-dodeliSatnicu?faces-redirect=true";
        }
        
        pregledPrijave.setStatus("Odobrena");
        SajamDAO.izmeniStatusPrijave(pregledPrijave);

        return "/administrator/admin-pregledPristiglihPrijava?faces-redirect=true";
    }

    public String dodeliSatnice() throws SQLException {
        pregledPrijave.setStatus("Odobrena");
        SajamDAO.izmeniStatusPrijave(pregledPrijave);
        
        for(Satnica st : rezervacijaPredavanja){
            st.setStatus("Rezevisana");
            SajamDAO.rezervisiSatnicu(st, pregledPrijave.getIdKompanija());
        }
        
        for(Satnica st : rezervacijaRadionica){
            st.setStatus("Rezevisana");
            SajamDAO.rezervisiSatnicu(st, pregledPrijave.getIdKompanija());
        }
        for(Satnica st : rezervacijaPrezentacije){
            st.setStatus("Rezevisana");
            SajamDAO.rezervisiSatnicu(st, pregledPrijave.getIdKompanija());
        }

        return "/administrator/admin-pregledPristiglihPrijava?faces-redirect=true";
    }

    public String dohvatiDatum(Date d) {
        return d.toString();
    }
    
    public String odbiPrijavuLink(){
        
        return "/administrator/admin-pregledPrijave-napomena?faces-redirect=true";
    }

    public String odbiPrijavu() throws SQLException {
        pregledPrijave.setStatus("Odbijena");
        
        SajamDAO.izmeniStatusPrijave(pregledPrijave);
        SajamDAO.izmeniNapomenu(pregledPrijave);
        return "/administrator/admin-pregledPristiglihPrijava?faces-redirect=true";
    }

    public String dohvatiNazivKompanije(int idkompanija) throws SQLException {
        return KompanijaDAO.dohvatiNaziv(idkompanija);
    }

    public ControllerAdminPrijave() throws SQLException {
        this.svePrijave = SajamDAO.dohvatiSvePrijave(1);
    }

}
