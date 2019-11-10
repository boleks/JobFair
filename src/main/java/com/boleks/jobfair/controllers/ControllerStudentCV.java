package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.*;
import com.boleks.jobfair.util.dao.StudentCvDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named(value = "controllerStudentCV")
@SessionScoped
public class ControllerStudentCV implements Serializable {

    private CV studentCV = new CV();
    private List<Obrazovanje> obrazovanjeLista = new ArrayList<>();
    private List<RadnoIskustvo> radnoIskustvoLista = new ArrayList<>();
    private List<Jezik> jezikLista = new ArrayList<>();
    private List<KompjuterskeVestine> kompjuterskeVestineLista = new ArrayList<>();
    private Obrazovanje dodajObrazovanje;
    private RadnoIskustvo dodajRadnoIskustvo;
    private Jezik dodajJezik;
    private KompjuterskeVestine dodajKompjuterskeVestine;

    public CV getStudentCV() {
        return studentCV;
    }

    public void setStudentCV(CV studentCV) {
        this.studentCV = studentCV;
    }

    public List<Obrazovanje> getObrazovanjeLista() {
        return obrazovanjeLista;
    }

    public void setObrazovanjeLista(List<Obrazovanje> obrazovanjeLista) {
        this.obrazovanjeLista = obrazovanjeLista;
    }

    public List<RadnoIskustvo> getRadnoIskustvoLista() {
        return radnoIskustvoLista;
    }

    public void setRadnoIskustvoLista(List<RadnoIskustvo> radnoIskustvoLista) {
        this.radnoIskustvoLista = radnoIskustvoLista;
    }

    public Obrazovanje getDodajObrazovanje() {
        return dodajObrazovanje;
    }

    public void setDodajObrazovanje(Obrazovanje dodajObrazovanje) {
        this.dodajObrazovanje = dodajObrazovanje;
    }

    public RadnoIskustvo getDodajRadnoIskustvo() {
        return dodajRadnoIskustvo;
    }

    public void setDodajRadnoIskustvo(RadnoIskustvo dodajRadnoIskustvo) {
        this.dodajRadnoIskustvo = dodajRadnoIskustvo;
    }

    public List<Jezik> getJezikLista() {
        return jezikLista;
    }

    public void setJezikLista(List<Jezik> jezikLista) {
        this.jezikLista = jezikLista;
    }

    public List<KompjuterskeVestine> getKompjuterskeVestineLista() {
        return kompjuterskeVestineLista;
    }

    public void setKompjuterskeVestineLista(List<KompjuterskeVestine> kompjuterskeVestineLista) {
        this.kompjuterskeVestineLista = kompjuterskeVestineLista;
    }

    public Jezik getDodajJezik() {
        return dodajJezik;
    }

    public void setDodajJezik(Jezik dodajJezik) {
        this.dodajJezik = dodajJezik;
    }

    public KompjuterskeVestine getDodajKompjuterskeVestine() {
        return dodajKompjuterskeVestine;
    }

    public void setDodajKompjuterskeVestine(KompjuterskeVestine dodajKompjuterskeVestine) {
        this.dodajKompjuterskeVestine = dodajKompjuterskeVestine;
    }
    
    
    
    
    
    //Metode
    public String dodajObrazovanjeLink() {
        dodajObrazovanje = new Obrazovanje();
        return "/student/studentCV-dodajObrazovanje?faces-redirect=true";
    }

    public String dodajObrazovanjeUListu() {
        obrazovanjeLista.add(dodajObrazovanje);
        dodajObrazovanje = null;
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }

    public String obrisiObrazovanjeIzListe(Obrazovanje obrisiOrazovanje) {
        obrazovanjeLista.remove(obrisiOrazovanje);
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }
    
    public String dodajRanoIskustvoLink() {
        dodajRadnoIskustvo = new RadnoIskustvo();
        return "/student/studentCV-dodajRadnoIskustvo?faces-redirect=true";
    }

    public String dodajRadnoIskutvoUListu() {
        radnoIskustvoLista.add(dodajRadnoIskustvo);
        dodajRadnoIskustvo = null;
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }

    public String obrisiRadnoIskustvoIzListe(RadnoIskustvo obrisiRadnoIskutvo) {
        radnoIskustvoLista.remove(obrisiRadnoIskutvo);
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }
    
    public String dodajJezikLink() {
        dodajJezik = new Jezik();
        return "/student/studentCV-dodajJezik?faces-redirect=true";
    }

    public String dodajJezikUListu() {
        jezikLista.add(dodajJezik);
        dodajJezik = null;
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }

    public String obrisiJezikIzListe(Jezik obrisiJezik) {
        jezikLista.remove(obrisiJezik);
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }
    
    public String dodajKompjuterskuVestinuLink() {
        dodajKompjuterskeVestine = new KompjuterskeVestine();
        return "/student/studentCV-dodajKompjuterskuVestinu?faces-redirect=true";
    }

    public String dodajKompjuterskuVestinuUListu() {
        kompjuterskeVestineLista.add(dodajKompjuterskeVestine);
        dodajKompjuterskeVestine = null;
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }

    public String obrisiKompjuterskuVestinu(KompjuterskeVestine obrisiKompjuterskuVestinu) {
        kompjuterskeVestineLista.remove(obrisiKompjuterskuVestinu);
        return "/student/studentCV-kreiraj?faces-redirect=true";
    }
    
    //Snimi CV
    public String snimiCV() throws SQLException{
        studentCV.setObrazivanje(obrazovanjeLista);
        studentCV.setRadnoIskustvo(radnoIskustvoLista);
        studentCV.setJezik(jezikLista);
        studentCV.setKompjuterskeVestine(kompjuterskeVestineLista);
        
        studentCV.setIdCV(StudentCvDAO.snimiCVosnovniPodaci(studentCV));
        
        for(int i = 0; i <studentCV.getObrazivanje().size(); i++){                                            
            studentCV.getObrazivanje().get(i).setIdCV(studentCV.getIdCV());
            StudentCvDAO.snimiCVObrazovanje(studentCV.getObrazivanje().get(i));
        }
        
        for(int i = 0; i < studentCV.getRadnoIskustvo().size(); i++){                                            
            studentCV.getRadnoIskustvo().get(i).setIdCV(studentCV.getIdCV());
            StudentCvDAO.snimiCVRadnoIskustvo(studentCV.getRadnoIskustvo().get(i));
        }
        
        for(int i = 0; i < studentCV.getJezik().size(); i++){                                            
            studentCV.getJezik().get(i).setIdCV(studentCV.getIdCV());
            StudentCvDAO.snimiCVJezik(studentCV.getJezik().get(i));
        }
        
        for(int i = 0; i < studentCV.getKompjuterskeVestine().size(); i++){                                            
            studentCV.getKompjuterskeVestine().get(i).setIdCV(studentCV.getIdCV());
            StudentCvDAO.snimiCVKompjuterskeVestine(studentCV.getKompjuterskeVestine().get(i));
        }
        
        return "/index-student?faces-redirect=true";
                        
    }
    
    
    //Pregled i izmena CV
    
    
    
    
    
    
    public int sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        studentCV.setIdKorisnik((int) session.getAttribute("KorisnikID"));
        return studentCV.getIdKorisnik();
      }
    
        
    public ControllerStudentCV() {
    }

}
