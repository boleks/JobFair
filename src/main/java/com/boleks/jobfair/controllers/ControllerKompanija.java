package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Delatnost;
import com.boleks.jobfair.util.beans.Gradovi;
import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.KorisnikDAO;
import com.boleks.jobfair.util.dao.ListeDAO;

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
import java.sql.SQLException;
import java.util.List;

@Named(value = "controllerKompanija")
@SessionScoped
public class ControllerKompanija implements Serializable {
    
    private Kompanija kompanija;
    private int idKorisnik;
       
    private String staraLozinka;
    private String novaLozinka;
    private String novaLozinkaPonovo;
    private Part kompanijaSlika;
    private final List<Gradovi> gradovi;
    private final List<Delatnost> delatnosti;
    
                          
            
    public Kompanija getKompanija() {
        return kompanija;
    }
    
    public void setKompanija() throws SQLException {        
        this.kompanija = KompanijaDAO.dohvatiKompanija(idKorisnik);
    }

    public int getIdKorisnik() {
        return idKorisnik;
    }
    
    public String nazivKompanija() throws SQLException{
        if(kompanija == null){
            sesijaKorisnikID();
            setKompanija();
        }        
        return kompanija.getNaziv();
    }

    public Part getKompanijaSlika() {
        return kompanijaSlika;
    }

    public void setKompanijaSlika(Part kompanijaSlika) {
        this.kompanijaSlika = kompanijaSlika;
    }

    public List<Gradovi> getGradovi() {
        return gradovi;
    }

    public List<Delatnost> getDelatnosti() {
        return delatnosti;
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
            
    public void sesijaKorisnikID() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        idKorisnik = (int) session.getAttribute("KorisnikID");
      }
    
    public String izmeniPodatke() throws SQLException, IOException{
        
        if (kompanijaSlika != null) {
                        
            BufferedImage bi = ImageIO.read(kompanijaSlika.getInputStream());
            int width = bi.getWidth();
            int height = bi.getHeight();            
            
            if(width < 100 || width > 300 || height < 100 || width >300 ){
                String greska = "Dimenzije slike moraju biti najmanje 100x100px i najvise 300x300px.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-izmena-podataka";
            }
            
            if (kompanijaSlika.getContentType().equals("image/jpeg") || kompanijaSlika.getContentType().equals("image/png")) {
                String[] ekstenzija = kompanijaSlika.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                String imeSlike = kompanija.getKorisnickoIme() + "." + ekstenzija[ekstenzija.length - 1];
                boolean upload = new FileUpload().saveFile(kompanijaSlika, imeSlike);
                if (upload) {
                    kompanija.setSlika(imeSlike);
                }
            } else {
                String greska = "Dozvoljene su samo .jpg i .png formati slika.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/kompanija/kompanija-izmena-podataka";
            }
        }
        
        if(KompanijaDAO.izmeniPodatkeKompanija(kompanija)){
            return "/index-kompanija?faces-redirect=true";
        }
        String greska = "Podaci nisu uspesno snimljeni. Proverite da li ste ispravno uneli podatke.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));        
        return "/kompanija/kompanija-izmena-podataka?faces-redirect=true";
    }
    
    public String izmeniLozinkuKompanija() throws SQLException{
        
        if(staraLozinka.equals(this.kompanija.getLozinka()) && novaLozinka.equals(novaLozinkaPonovo)){
            
            if(KorisnikDAO.izmenaLozinke(this.kompanija.getIdKorisnik(), novaLozinka))
            {
                this.kompanija.setLozinka(novaLozinka);
                return "/index-kompanija?faces-redirect=true";
            }                        
        }
        String greska = "Podaci nisu ispavni. Proverite ponovo.";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));        
        return "/kompanija/kompanija-promena-lozinke";                
    }
                  
    public ControllerKompanija() throws SQLException {
        this.gradovi = ListeDAO.dohvatiGradove();
        this.delatnosti = ListeDAO.dohvatiDelatnost();        
    }        
    
}
