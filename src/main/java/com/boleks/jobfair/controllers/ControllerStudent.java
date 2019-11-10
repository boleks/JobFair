package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.Enkripcija;
import com.boleks.jobfair.util.beans.Student;
import com.boleks.jobfair.util.dao.KorisnikDAO;
import com.boleks.jobfair.util.dao.StudentCvDAO;
import com.boleks.jobfair.util.dao.StudentDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@Named(value = "controllerStudent")
@SessionScoped
public class ControllerStudent implements Serializable {

    private Student student;
    private int idKorisnik;
    private boolean diplomiraoChecked = false;

    private String staraLozinka;
    private String novaLozinka;
    private String novaLozinkaPonovo;

    private Part studentSlika;

    public Student getStudent() {
        return student;
    }

    public void setStudent() throws SQLException {
        this.student = StudentDAO.dohvatiStudenta(idKorisnik);
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }

    public String imePrezimeStudenta() throws SQLException {
        if (student == null) {
            sesijaKorisnikID();
            setStudent();
        }
        return student.getIme() + " " + student.getPrezime();
    }

    public boolean isDiplomiraoChecked() {
        return diplomiraoChecked;
    }

    public void setDiplomiraoChecked(boolean diplomiraoChecked) {
        this.diplomiraoChecked = diplomiraoChecked;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getNovaLozinka() {
        return novaLozinka;
    }

    public void setNovaLozinka(String novaLozinka) {
        this.novaLozinka = novaLozinka;
    }

    public String getNovaLozinkaPonovo() {
        return novaLozinkaPonovo;
    }

    public void setNovaLozinkaPonovo(String novaLozinkaPonovo) {
        this.novaLozinkaPonovo = novaLozinkaPonovo;
    }

    public Part getStudentSlika() {
        return studentSlika;
    }

    public void setStudentSlika(Part studentSlika) {
        this.studentSlika = studentSlika;
    }

    public void sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        idKorisnik = (int) session.getAttribute("KorisnikID");
    }
    
    public boolean studentImaCV() throws SQLException{
        return StudentCvDAO.korisnikImaCV(student.getIdKorisnik());
    }

    public String izmeniPodatke() throws SQLException, IOException {
        if (diplomiraoChecked == true) {
            student.setDiplomirao('D');
        } else {
            student.setDiplomirao('N');
        }
        if (studentSlika != null) {
                        
            BufferedImage bi = ImageIO.read(studentSlika.getInputStream());
            int width = bi.getWidth();
            int height = bi.getHeight();            
            
            if(width < 100 || width > 300 || height < 100 || width >300 ){
                String greska = "Dimenzije slike moraju biti najmanje 100x100px i najvise 300x300px.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/student/student-izmena-podataka";
            }
            
            if (studentSlika.getContentType().equals("image/jpeg") || studentSlika.getContentType().equals("image/png")) {
                String[] ekstenzija = studentSlika.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                String imeSlike = student.getKorisnickoIme() + "." + ekstenzija[ekstenzija.length - 1];
                boolean upload = new FileUpload().saveFile(studentSlika, imeSlike);
                if (upload) {
                    student.setSlika(imeSlike);
                }
            } else {
                String greska = "Dozvoljene su samo .jpg i .png formati slika.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/student/student-izmena-podataka";
            }
        }

        if (StudentDAO.izmeniPodatkeStudent(student)) {
            return "/index-student?faces-redirect=true";
        }
        String greska = "Podaci nisu uspesno sniljeni. Proverite da li ste ispravno uneli podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/student-izmena-podataka";
    }

    public String izmeniLozinkuStudent() throws SQLException, NoSuchAlgorithmException {
        staraLozinka = Enkripcija.enkripcijaLozinke(staraLozinka);
        novaLozinka = Enkripcija.enkripcijaLozinke(novaLozinka);
        novaLozinkaPonovo = Enkripcija.enkripcijaLozinke(novaLozinkaPonovo);
        
        if (staraLozinka.equals(this.student.getLozinka()) && novaLozinka.equals(novaLozinkaPonovo)) {

            if (KorisnikDAO.izmenaLozinke(this.student.getIdKorisnik(), novaLozinka)) {
                this.student.setLozinka(novaLozinka);
                return "/index-student?faces-redirect=true";
            }
        }
        String greska = "Podaci nisu ispavni. Proverite ponovo.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
        return "/student/student-promena-lozinke";
    }

    public ControllerStudent() throws SQLException {
    }

}
