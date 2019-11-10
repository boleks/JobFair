package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.CV;
import com.boleks.jobfair.util.beans.Konkurs;
import com.boleks.jobfair.util.beans.KonkursPrijava;
import com.boleks.jobfair.util.beans.PropratnoPismo;
import com.boleks.jobfair.util.dao.KonkursDAO;
import com.boleks.jobfair.util.dao.StudentCvDAO;
import com.boleks.jobfair.util.dao.StudentDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "controllerKompanijaPregledKonkursa")
@SessionScoped
public class ControllerKompanijaPregledKonkursa implements Serializable {

    private Integer idKompanija;
    private List<Konkurs> konkursiLista;
    private Integer idKonkurs;
    private KonkursPrijava pregledPrijave;
    private List<KonkursPrijava> prijave;
    private CV cvPrijave;
    private PropratnoPismo ppPrijave;    

    public Integer getIdKompanija() {
        return idKompanija;
    }

    public void setIdKompanija(Integer idKompanija) {
        this.idKompanija = idKompanija;
    }

    public List<Konkurs> getKonkursiLista() {
        return konkursiLista;
    }

    public void setKonkursiLista(List<Konkurs> konkursiLista) {
        this.konkursiLista = konkursiLista;
    }

    public Integer getIdKonkurs() {
        return idKonkurs;
    }

    public void setIdKonkurs(Integer idKonkurs) {
        this.idKonkurs = idKonkurs;
    }

    public KonkursPrijava getPregledPrijave() {
        return pregledPrijave;
    }

    public void setPregledPrijave(KonkursPrijava pregledPrijave) {
        this.pregledPrijave = pregledPrijave;
    }

    public List<KonkursPrijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(List<KonkursPrijava> prijave) {
        this.prijave = prijave;
    }

    public CV getCvPrijave() {
        return cvPrijave;
    }

    public void setCvPrijave(CV cvPrijave) {
        this.cvPrijave = cvPrijave;
    }

    public PropratnoPismo getPpPrijave() {
        return ppPrijave;
    }

    public void setPpPrijave(PropratnoPismo ppPrijave) {
        this.ppPrijave = ppPrijave;
    }
    
    

    public void sesijaKompanijaID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        idKompanija = (int) session.getAttribute("KorisnikID");
    }

    public void  dohvatiKonkurse() {        
        sesijaKompanijaID();
        if (idKompanija != null) {
            try {
                konkursiLista = KonkursDAO.konkursiKompanije(idKompanija);
                
            } catch (SQLException ex) {
                Logger.getLogger(ControllerKompanijaPregledKonkursa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }

    public int brojPristiglihPrijava(int idKonkurs) throws SQLException {
        return KonkursDAO.brojPristiglihPrijava(idKonkurs);
    }

    public String pregledajKonkurs(Integer idKonkurs) throws SQLException {
        prijave = KonkursDAO.dohvatiSvePrijaveNaKonkurs(idKonkurs);
        
        return "/kompanija/kompanija-pregled-prijava-na-konkurs?faces-redirect=true";
    }
    
    public String pregledajCV(int idStudent) throws SQLException {
        
        cvPrijave = StudentCvDAO.dohvatiCV(idStudent);
        
        return "/kompanija/kompanija-pregledajCV?faces-redirect=true";
    }
    
    public String pregledajPP(int idPrijava) throws SQLException {
        
        ppPrijave = KonkursDAO.dohvatiPP(idPrijava);
        
        return "/kompanija/kompanija-pregledajPP?faces-redirect=true";
    }
        
    public String oceniPrijavuLink(KonkursPrijava kp) throws SQLException {
        
        pregledPrijave = kp;
                        
        return "/kompanija/kompanija-oceniPrijavu?faces-redirect=true";
    }
    
    public String oceniPrijavu() throws SQLException {                
        
        if(KonkursDAO.oceniPrijavu(pregledPrijave)){
            return "/kompanija/kompanija-pregled-prijava-na-konkurs?faces-redirect=true";
        }else{
            String greska = "Doslo je do greske.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-oceniPrijavu";
        }                        
    }
    
    public String statusPrijaveLink(KonkursPrijava kp) throws SQLException {
        
        pregledPrijave = kp;
                        
        return "/kompanija/kompanija-statusPrijave?faces-redirect=true";
    }
    
    public String dodeliStatusPrijave() throws SQLException {                
        pregledPrijave.setDatumIzbora(LocalDate.now());
        if(KonkursDAO.dodeliStatusPrijave(pregledPrijave)){
            return "/kompanija/kompanija-pregled-prijava-na-konkurs?faces-redirect=true";
        }else{
            String greska = "Doslo je do greske.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-statusPrijave";
        }                        
    }
    
    public String dohvatiImePrezimeKandidata(int idStudent) throws SQLException {
        return StudentDAO.imePrezimeStudent(idStudent);
    }

}
