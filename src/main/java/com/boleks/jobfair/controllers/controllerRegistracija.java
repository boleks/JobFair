package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.Enkripcija;
import com.boleks.jobfair.util.beans.Delatnost;
import com.boleks.jobfair.util.beans.Gradovi;
import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.beans.Student;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.KorisnikDAO;
import com.boleks.jobfair.util.dao.ListeDAO;
import com.boleks.jobfair.util.dao.StudentDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@Named(value = "controllerRegistracija")
@SessionScoped
public class controllerRegistracija implements Serializable {
    
    private Student student = new Student();
    private Kompanija kompanija = new Kompanija();
    private boolean diplomiraoChecked = false;
    private boolean uspesnaRegistracijaStudent = false;
    private boolean korisnikPostoji = false;
    private boolean korisnikPostojiK = false;
    private String korisnickoImePostoji = "Korisnicko ime je zauzeto. Izaberite drugo";
    private String korisnickoImePostojiK = "Korisnicko ime je zauzeto. Izaberite drugo";
    private final List<Gradovi> gradovi;
    private final List<Delatnost> delatnosti;
    private Part slikaStudent;
    private Part slikaKompanija;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {

        this.student = student;
    }

    public boolean isDiplomiraoChecked() {
        return diplomiraoChecked;
    }

    public void setDiplomiraoChecked(boolean diplomiraoChecked) {
        this.diplomiraoChecked = diplomiraoChecked;
    }

    public boolean isUspesnaRegistracijaStudent() {
        return uspesnaRegistracijaStudent;
    }

    public void setUspesnaRegistracijaStudent(boolean uspesnaRegistracijaStudent) {
        this.uspesnaRegistracijaStudent = uspesnaRegistracijaStudent;
    }

    public boolean isKorisnikPostoji() {
        return korisnikPostoji;
    }

    public void setKorisnikPostoji(boolean korisnikPostoji) {
        this.korisnikPostoji = korisnikPostoji;
    }

    public Kompanija getKompanija() {
        return kompanija;
    }

    public void setKompanija(Kompanija kompanija) {
        this.kompanija = kompanija;
    }

    public String getKorisnickoImePostoji() {
        return korisnickoImePostoji;
    }

    public void setKorisnickoImePostoji(String korisnickoImePostoji) {
        this.korisnickoImePostoji = korisnickoImePostoji;
    }

    public boolean isKorisnikPostojiK() {
        return korisnikPostojiK;
    }

    public void setKorisnikPostojiK(boolean korisnikPostojiK) {
        this.korisnikPostojiK = korisnikPostojiK;
    }

    public String getKorisnickoImePostojiK() {
        return korisnickoImePostojiK;
    }

    public void setKorisnickoImePostojiK(String korisnickoImePostojiK) {
        this.korisnickoImePostojiK = korisnickoImePostojiK;
    }

    public List<Gradovi> getGradovi() {
        return gradovi;
    }

    public List<Delatnost> getDelatnosti() {
        return delatnosti;
    }

    public Part getSlikaStudent() {
        return slikaStudent;
    }

    public void setSlikaStudent(Part slikaStudent) {
        this.slikaStudent = slikaStudent;
    }

    public Part getSlikaKompanija() {
        return slikaKompanija;
    }

    public void setSlikaKompanija(Part slikaKompanija) {
        this.slikaKompanija = slikaKompanija;
    }

    public String registracijaStudent() throws SQLException, NoSuchAlgorithmException, IOException {

        if (korisnikPostoji(student.getKorisnickoIme())) {
            korisnikPostoji = true;
            return "registracija?faces-redirect=true";
        }

        student.setTip('S');

        //Uljuciti za enkripciju
        student.setLozinka(Enkripcija.enkripcijaLozinke(student.getLozinka())); 
        if (diplomiraoChecked == true) {
            student.setDiplomirao('D');
        } else {
            student.setDiplomirao('N');
        }
        
        BufferedImage bi = ImageIO.read(slikaStudent.getInputStream());
        int width = bi.getWidth();
        int height = bi.getHeight();

        if (width < 100 || width > 300 || height < 100 || width > 300) {
            String greska = "Dimenzije slike moraju biti najmanje 100x100px i najvise 300x300px.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/student/student-izmena-podataka";
        }

        if (slikaStudent.getContentType().equals("image/jpeg") || slikaStudent.getContentType().equals("image/png")) {
            String[] ekstenzija = slikaStudent.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
            String imeSlike = student.getKorisnickoIme() + "." + ekstenzija[ekstenzija.length - 1];
            boolean upload = new FileUpload().saveFile(slikaStudent, imeSlike);
            if (upload) {
                student.setSlika(imeSlike);
            }
        } else {
            String greska = "Dozvoljene su samo .jpg i .png format slika.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/registracija";
        }

        if (StudentDAO.registracijaStudent(student)) {
            student = new Student();
            return "/index?faces-redirect=true";
        }

        return "/registracija?faces-redirect=true";

    }

    public boolean korisnikPostoji(String korisnickoime) throws SQLException {
        return KorisnikDAO.proveraKorisnikPostoji(korisnickoime);
    }

    public String registracijaKompanija() throws SQLException, IOException, NoSuchAlgorithmException {

        if (korisnikPostoji(kompanija.getKorisnickoIme())) {
            korisnikPostojiK = true;
            return "/registracija?faces-redirect=true";
        }

        kompanija.setTip('K');
        
        kompanija.setLozinka(Enkripcija.enkripcijaLozinke(kompanija.getLozinka()));
        BufferedImage bi = ImageIO.read(slikaKompanija.getInputStream());
        int width = bi.getWidth();
        int height = bi.getHeight();

        if (width < 100 || width > 300 || height < 100 || width > 300) {
            String greska = "Dimenzije slike moraju biti najmanje 100x100px i najvise 300x300px.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/registracija";
        }

        if (slikaKompanija.getContentType().equals("image/jpeg") || slikaKompanija.getContentType().equals("image/png")) {
            String[] ekstenzija = slikaKompanija.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
            String imeSlike = kompanija.getKorisnickoIme() + "." + ekstenzija[ekstenzija.length - 1];
            boolean upload = new FileUpload().saveFile(slikaKompanija, imeSlike);
            if (upload) {
                kompanija.setSlika(imeSlike);
            }
        } else {
            String greska = "Dozvoljene su samo .jpg i .png format slika.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/registracija";
        }

        if (KompanijaDAO.registracijaKompanija(kompanija)) {
            kompanija = new Kompanija();
            return "/index?faces-redirect=true";
        }
        return "/registracija?faces-redirect=true";
    }
   
    public controllerRegistracija() throws SQLException {
        this.gradovi = ListeDAO.dohvatiGradove();
        this.delatnosti = ListeDAO.dohvatiDelatnost();

    }

}
