package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.*;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.KonkursDAO;
import com.boleks.jobfair.util.dao.StudentCvDAO;
import com.boleks.jobfair.util.dao.StudentDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Named(value = "controllerStudentKonkursPrijava")
@SessionScoped
public class ControllerStudentKonkursPrijava implements Serializable {

    private KonkursPrijava studentKonkursPrijava;
    private PropratnoPismo studentPropratnoPismo;
    private Konkurs prijavaKonkurs;
    private Student student;
    private List<KonkursPrijava> studentPrijave;
    private List<KonkursPrijava> svePrijaveKonkus;
    private String nazivKonkursaPrijave;
    private OcenaKompanije ocenaKompanije;
    private Kompanija kompanijaOcena;

    public KonkursPrijava getStudentKonkursPrijava() {
        return studentKonkursPrijava;
    }

    public void setStudentKonkursPrijava(KonkursPrijava studentKonkursPrijava) {
        this.studentKonkursPrijava = studentKonkursPrijava;
    }

    public PropratnoPismo getStudentPropratnoPismo() {
        return studentPropratnoPismo;
    }

    public void setStudentPropratnoPismo(PropratnoPismo studentPropratnoPismo) {
        this.studentPropratnoPismo = studentPropratnoPismo;
    }

    public Konkurs getStudentKonkurs() {
        return prijavaKonkurs;
    }

    public void setStudentKonkurs(Konkurs studentKonkurs) {
        this.prijavaKonkurs = studentKonkurs;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Konkurs getPrijavaKonkurs() {
        return prijavaKonkurs;
    }

    public void setPrijavaKonkurs(Konkurs prijavaKonkurs) {
        this.prijavaKonkurs = prijavaKonkurs;
    }

    public List<KonkursPrijava> getStudentPrijave() {
        return studentPrijave;
    }

    public void setStudentPrijave(List<KonkursPrijava> studentPrijave) {
        this.studentPrijave = studentPrijave;
    }

    public List<KonkursPrijava> getSvePrijaveKonkus() {
        return svePrijaveKonkus;
    }

    public void setSvePrijaveKonkus(List<KonkursPrijava> svePrijaveKonkus) {
        this.svePrijaveKonkus = svePrijaveKonkus;
    }

    public String getNazivKonkursaPrijave() {
        return nazivKonkursaPrijave;
    }

    public void setNazivKonkursaPrijave(String nazivKonkursaPrijave) {
        this.nazivKonkursaPrijave = nazivKonkursaPrijave;
    }

    public OcenaKompanije getOcenaKompanije() {
        return ocenaKompanije;
    }

    public void setOcenaKompanije(OcenaKompanije ocenaKompanije) {
        this.ocenaKompanije = ocenaKompanije;
    }

    public Kompanija getKompanijaOcena() {
        return kompanijaOcena;
    }

    public void setKompanijaOcena(Kompanija kompanijaOcena) {
        this.kompanijaOcena = kompanijaOcena;
    }
    
    

    public String konkursPrijavaLink(int idKonkurs) throws SQLException {
        studentKonkursPrijava = new KonkursPrijava();
        studentPropratnoPismo = new PropratnoPismo();
        studentKonkursPrijava.setIdKonkurs(idKonkurs);
        prijavaKonkurs = KonkursDAO.dohvatiKonkurs(idKonkurs);
        student = StudentDAO.dohvatiStudenta(sesijaKorisnikID());
        studentKonkursPrijava.setIdStudent(student.getIdKorisnik());

        return "/student/student-prijava-na-konkurs?faces-redirect=true";
    }

    public boolean studentImaCV() throws SQLException {
        return StudentCvDAO.korisnikImaCV(student.getIdKorisnik());
    }

    public String konkursPrijavaStudent() throws SQLException {
        studentKonkursPrijava.setDatumPrijave(LocalDate.now());
        studentKonkursPrijava.setIzabran('C');
        studentKonkursPrijava.setOcenaPrijave(0);
        studentKonkursPrijava.setIdCV(StudentCvDAO.dohvatiIdCV(student.getIdKorisnik()));

        int idPrijava = KonkursDAO.prijavaStudentaNaKonkurs(studentKonkursPrijava);
        studentPropratnoPismo.setIdPrijave(idPrijava);

        int idPropratnoPismo = KonkursDAO.prijavaStudentaPropratnoPismo(studentPropratnoPismo);
        studentPropratnoPismo.setIdPropratnoPismo(idPropratnoPismo);

        return "/index-student?faces-redirect=true";
    }

    public void dohvatiPrijave() throws SQLException {
        studentPrijave = KonkursDAO.dohvatiSvePrijaveStudenta(sesijaKorisnikID());
    }

    public String dohvatiNazivKonkursa(int idKonkurs) throws SQLException {
        return KonkursDAO.dohvatiKonkurs(idKonkurs).getNazivPozicija();
    }

    public String dohvatiImePrezimeKandidata(int idStudent) throws SQLException {
        return StudentDAO.imePrezimeStudent(idStudent);
    }

    public String pregledKandidata(int idKonkurs) throws SQLException {
        nazivKonkursaPrijave = dohvatiNazivKonkursa(idKonkurs);
        svePrijaveKonkus = KonkursDAO.dohvatiSvePrijaveNaKonkurs(idKonkurs);

        return "/student/student-prijava-kandidati?faces-redirect=true";
    }

    public boolean oceniUslov(KonkursPrijava kp) throws SQLException {

        if (kp.getIzabran() == 'D' && kp.getDatumIzbora().plusDays(30).isAfter(LocalDate.now()) && KonkursDAO.studentOcenioKompaniju(KonkursDAO.dohvatiIdKompanije(kp.getIdKonkurs()), kp.getIdStudent()) != true) {
            return true;
        }
        return false;
    }

    public String oceniPoslodavcaLink(int idKonkurs) throws SQLException {        
        ocenaKompanije = new OcenaKompanije();
        ocenaKompanije.setIdStudent(sesijaKorisnikID());
        ocenaKompanije.setIdKompanija(KonkursDAO.dohvatiIdKompanije(idKonkurs));
        kompanijaOcena = KompanijaDAO.dohvatiKompanija(ocenaKompanije.getIdKompanija());        
        
        return "/student/student-oceni-kompaniju?faces-redirect=true";
    }
    
    public String oceniPoslodavca() {
        ocenaKompanije.setDatum(LocalDate.now());
                        
        return "/student/student-prijave?faces-redirect=true";
    }
    
    public int sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (int) session.getAttribute("KorisnikID");

    }

    /**
     * Creates a new instance of controllerStudenKonkursPrijava
     */
    public ControllerStudentKonkursPrijava() {
    }

}
