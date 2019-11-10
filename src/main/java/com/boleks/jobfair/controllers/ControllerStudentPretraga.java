package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Delatnost;
import com.boleks.jobfair.util.beans.Gradovi;
import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.beans.Konkurs;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.KonkursDAO;
import com.boleks.jobfair.util.dao.ListeDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Named(value = "controllerStudentPretraga")
@SessionScoped
public class ControllerStudentPretraga implements Serializable {

    private String nazivKompanije;
    private int[] grad;
    private int[] delatnost;
    private final List<Gradovi> gradoviLista;
    private final List<Delatnost> delatnostLista;

    private String nazivKompanijeKonkurs;
    private String nazivKonkursa;
    private char[] tipKonkusa;

    private List<Kompanija> kompanijeRezultat;
    private List<Konkurs> konkursiRezultat;
    private Kompanija kompanijaPregled;
    private Konkurs konkursPregled;
    private List<Konkurs> konkursiKompanije;
   

    public String getNazivKompanije() {
        return nazivKompanije;
    }

    public void setNazivKompanije(String nazivKompanije) {
        this.nazivKompanije = nazivKompanije;
    }

    public int[] getGrad() {
        return grad;
    }

    public void setGrad(int[] grad) {
        this.grad = grad;
    }

    public int[] getDelatnost() {
        return delatnost;
    }

    public void setDelatnost(int[] delatnost) {
        this.delatnost = delatnost;
    }

    public List<Kompanija> getKompanijeRezultat() {
        return kompanijeRezultat;
    }

    public void setKompanijeRezultat(List<Kompanija> kompanijeRezultat) {
        this.kompanijeRezultat = kompanijeRezultat;
    }

    public List<Gradovi> getGradoviLista() {
        return gradoviLista;
    }

    public List<Delatnost> getDelatnostLista() {
        return delatnostLista;
    }

    public String getNazivKompanijeKonkurs() {
        return nazivKompanijeKonkurs;
    }

    public void setNazivKompanijeKonkurs(String nazivKompanijeKonkurs) {
        this.nazivKompanijeKonkurs = nazivKompanijeKonkurs;
    }

    public String getNazivKonkursa() {
        return nazivKonkursa;
    }

    public void setNazivKonkursa(String nazivKonkursa) {
        this.nazivKonkursa = nazivKonkursa;
    }

    public char[] getTipKonkusa() {
        return tipKonkusa;
    }

    public void setTipKonkusa(char[] tipKonkusa) {
        this.tipKonkusa = tipKonkusa;
    }

    public List<Konkurs> getKonkursiRezultat() {
        return konkursiRezultat;
    }

    public void setKonkursiRezultat(List<Konkurs> konkursiRezultat) {
        this.konkursiRezultat = konkursiRezultat;
    }

    public Kompanija getKompanijaPregled() {
        return kompanijaPregled;
    }

    public void setKompanijaPregled(Kompanija kompanijaPregled) {
        this.kompanijaPregled = kompanijaPregled;
    }

    public Konkurs getKonkursPregled() {
        return konkursPregled;
    }

    public void setKonkursPregled(Konkurs konkursPregled) {
        this.konkursPregled = konkursPregled;
    }

    public List<Konkurs> getKonkursiKompanije() {
        return konkursiKompanije;
    }

    public void setKonkursiKompanije(List<Konkurs> konkursiKompanije) {
        this.konkursiKompanije = konkursiKompanije;
    }                    

    public String pretragaKompanija() throws SQLException {
        if (nazivKompanije == null) {
            nazivKompanije = "";
        }
        kompanijeRezultat = KompanijaDAO.pretragaKompanijaStudent(nazivKompanije, delatnost, grad);

        return "/student/student-pretraga-kompanija";
    }

    public String pretragaKonkursa() throws SQLException {
        if (nazivKompanijeKonkurs == null) {
            nazivKompanijeKonkurs = "";
        }
        if (nazivKonkursa == null) {
            nazivKonkursa = "";
        }

        konkursiRezultat = KompanijaDAO.pretragaKonkursa(nazivKompanijeKonkurs, nazivKonkursa, tipKonkusa);

        return "/student/student-pretraga-konkursa";
    }

    public String dohvatiImeGrada(int idGrada) {
        return gradoviLista.get(idGrada - 1).getNaziv();
    }

    public String dohvatiDelatnostNaziv(int idDelatnosti) {
        return delatnostLista.get(idDelatnosti - 1).getNaziv();
    }

    public Kompanija dohvatiKompaniju(int idKompanija) throws SQLException {
        return KompanijaDAO.dohvatiKompanija(idKompanija);
    }

    public String opsirnijeKompanija(Kompanija k) throws SQLException {
        kompanijaPregled = k;
        konkursiKompanije = KonkursDAO.konkursiKompanije(kompanijaPregled.getIdKorisnik());
        return "/student/student-kompanija-opsirnije?faces-redirect=true";
    }

    public String opsirnijeKonkurs(Konkurs k) {
        konkursPregled = k;
        return "/student/student-konkurs-opsirnije?faces-redirect=true";
    }
    
    public boolean konkursProvera() throws SQLException{
        if(!studentKonkurisao() && !konkursIstekao()){
            return true;
        }
        return false;
    }
    
    public boolean studentKonkurisao() throws SQLException{       
        return KonkursDAO.studenKonkurisao(konkursPregled.getIdKonkurs(), sesijaKorisnikID());
    }
    public boolean konkursIstekao(){
        if(konkursPregled.getRokZaPrijavu().before(new Date(System.currentTimeMillis()))){
           return true;
        }
        return false;
    }
    
    public int sesijaKorisnikID() {
    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    return (int) session.getAttribute("KorisnikID");        
    }

    public ControllerStudentPretraga() throws SQLException {
        this.gradoviLista = ListeDAO.dohvatiGradove();
        this.delatnostLista = ListeDAO.dohvatiDelatnost();
    }
    
    

}
