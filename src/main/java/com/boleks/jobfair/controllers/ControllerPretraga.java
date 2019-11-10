package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Delatnost;
import com.boleks.jobfair.util.beans.Gradovi;
import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.ListeDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named(value = "controllerPretraga")
@SessionScoped
public class ControllerPretraga implements Serializable {

    private String nazivKompanije;
    private int[] grad;
    private int[] delatnost;
    private final List<Gradovi> gradoviLista;
    private final List<Delatnost> delatnostLista;

    private String nazivKompanijeKonkurs;
    private String nazivKonkursa;
    private char[] tipKonkusa;

    private List<Kompanija> kompanijeRezultat;
    private Kompanija kompanijaPregled;

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

    public List<Kompanija> getKompanijeRezultat() {
        return kompanijeRezultat;
    }

    public void setKompanijeRezultat(List<Kompanija> kompanijeRezultat) {
        this.kompanijeRezultat = kompanijeRezultat;
    }

    public Kompanija getKompanijaPregled() {
        return kompanijaPregled;
    }

    public void setKompanijaPregled(Kompanija kompanijaPregled) {
        this.kompanijaPregled = kompanijaPregled;
    }

    public List<Gradovi> getGradoviLista() {
        return gradoviLista;
    }

    public List<Delatnost> getDelatnostLista() {
        return delatnostLista;
    }

    public String pretragaKompanija() throws SQLException {
        if (nazivKompanije == null) {
            nazivKompanije = "";
        }
        kompanijeRezultat = KompanijaDAO.pretragaKompanijaStudent(nazivKompanije, delatnost, grad);

        return "/pretraga";
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
        return "/kompanija-opsirnije?faces-redirect=true";
    }
    
    public String provera(){
        if(kompanijaPregled == null){
            return "/pretraga?faces-redirect=true";
        }
        return null;
    }

    /**
     * Creates a new instance of ControllerPretraga
     */
    public ControllerPretraga() throws SQLException {
        this.gradoviLista = ListeDAO.dohvatiGradove();
        this.delatnostLista = ListeDAO.dohvatiDelatnost();
    }

}
