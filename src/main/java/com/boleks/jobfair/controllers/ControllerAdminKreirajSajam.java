package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.DodatneUsluge;
import com.boleks.jobfair.util.beans.Paket;
import com.boleks.jobfair.util.beans.Sajam;
import com.boleks.jobfair.util.beans.Satnica;
import com.boleks.jobfair.util.dao.SajamDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named(value = "controllerAdminKreirajSajam")
@SessionScoped
public class ControllerAdminKreirajSajam implements Serializable {

    private Sajam kreirajSajam = new Sajam();
    private List<Satnica> satnicaLista = new ArrayList<>();
    private List<Paket> paketLista = new ArrayList<>();
    private List<DodatneUsluge> dodatneUslugeLista = new ArrayList<>();
    private Paket dodajPaket;
    private DodatneUsluge dodajDodatneUsluge;
    private Part sajamSlika;

    public Sajam getKreirajSajam() {
        return kreirajSajam;
    }

    public void setKreirajSajam(Sajam kreirajSajam) {
        this.kreirajSajam = kreirajSajam;
    }

    public List<Satnica> getSatnicaLista() {
        return satnicaLista;
    }

    public void setSatnicaLista(List<Satnica> satnicaLista) {
        this.satnicaLista = satnicaLista;
    }

    public List<Paket> getPaketLista() {
        return paketLista;
    }

    public void setPaketLista(List<Paket> paketLista) {
        this.paketLista = paketLista;
    }

    public List<DodatneUsluge> getDodatneUslugeLista() {
        return dodatneUslugeLista;
    }

    public void setDodatneUslugeLista(List<DodatneUsluge> dodatneUslugeLista) {
        this.dodatneUslugeLista = dodatneUslugeLista;
    }

    public Paket getDodajPaket() {
        return dodajPaket;
    }

    public void setDodajPaket(Paket dodajPaket) {
        this.dodajPaket = dodajPaket;
    }

    public DodatneUsluge getDodajDodatneUsluge() {
        return dodajDodatneUsluge;
    }

    public void setDodajDodatneUsluge(DodatneUsluge dodajDodatneUsluge) {
        this.dodajDodatneUsluge = dodajDodatneUsluge;
    }

    public Part getSajamSlika() {
        return sajamSlika;
    }

    public void setSajamSlika(Part sajamSlika) {
        this.sajamSlika = sajamSlika;
    }

    //Metode//
    public String sajamKorakDva() throws IOException {

        if (!kreirajSajam.getDatumOd().before(kreirajSajam.getDatumDo())) {
            String greska = "Datum pocetka sajma mora biti pre datuma zavrsetka sajma.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/administrator/admin-kreirajSajam";
        }

        if (!kreirajSajam.getDatumStudent().before(kreirajSajam.getDatumOd()) || !kreirajSajam.getDatumKompanija().before(kreirajSajam.getDatumOd())) {
            String greska = "Datum za prijavu studenata i kompanija mora biti pre pocetka sajma.";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
            return "/administrator/admin-kreirajSajam";
        }

        if (sajamSlika != null) {

            BufferedImage bi = ImageIO.read(sajamSlika.getInputStream());
            int width = bi.getWidth();
            int height = bi.getHeight();

            if (width < 200 || width > 800 || height < 200 || width > 800) {
                String greska = "Dimenzije slike moraju biti najmanje 200x200px i najvise 800x800px.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/administrator/admin-kreirajSajam?faces-redirect=true";
            }

            if (sajamSlika.getContentType().equals("image/jpeg") || sajamSlika.getContentType().equals("image/png")) {
                String[] ekstenzija = sajamSlika.getSubmittedFileName().split("\\.(?=[^\\.]+$)");
                String imeSlike = kreirajSajam.getNaziv().trim() + "-" + LocalDate.now().toString() + "." + ekstenzija[ekstenzija.length - 1];
                boolean upload = new FileUpload().saveFile(sajamSlika, imeSlike);
                if (upload) {
                    kreirajSajam.setLogo(imeSlike);
                }
            } else {
                String greska = "Dozvoljene su samo .jpg i .png formati slika.";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, greska, null));
                return "/administrator/admin-kreirajSajam";
            }
        }

        KreirajSatnicuSajma();

        return "/administrator/admin-kreirajSajamDva?faces-redirect=true";
    }

    public void KreirajSatnicuSajma() {
        Date datumOd = new Date(kreirajSajam.getDatumOd().getTime());
        Date datumDo = new Date(kreirajSajam.getDatumDo().getTime());

        satnicaLista = new ArrayList<>();

        while (!datumDo.before(datumOd)) {

            for (int i = 10; i <= 15; i++) {
                Satnica satnica = new Satnica();
                satnica.setDatum(new Date(datumOd.getTime()));
                satnica.setVreme(i + ":00");
                satnicaLista.add(satnica);

            }
            long ltime = datumOd.getTime() + 1 * 24 * 60 * 60 * 1000;

            datumOd.setTime(ltime);

        }
    }

    public String sajamKorakTri() {

        return "/administrator/admin-kreirajSajamTri?faces-redirect=true";
    }

    public String dodajPaketuLIstu() {
        paketLista.add(dodajPaket);
        dodajPaket = null;
        return "/administrator/admin-kreirajSajamTri?faces-redirect=true";
    }

    public String obrisiPaketizListe(Paket obrisiPaket) {
        paketLista.remove(obrisiPaket);
        return "/administrator/admin-kreirajSajamTri?faces-redirect=true";

    }

    public String dodajPaketLink() {
        dodajPaket = new Paket();
        return "/administrator/admin-kreirajSajamDodajPaket?faces-redirect=true";

    }

    public String dodajDodatneUslugeLink() {
        dodajDodatneUsluge = new DodatneUsluge();
        return "/administrator/admin-kreirajSajamDodajUslugu?faces-redirect=true";

    }

    public String dodajDodatnuUsluguUListu() {
        dodatneUslugeLista.add(dodajDodatneUsluge);
        dodajDodatneUsluge = null;
        return "/administrator/admin-kreirajSajamTri?faces-redirect=true";
    }

    public String obrisiDodatnuUsluguizListe(DodatneUsluge obrisiDodatneUsluge) {
        dodatneUslugeLista.remove(obrisiDodatneUsluge);
        return "/administrator/admin-kreirajSajamTri?faces-redirect=true";
    }

    public String snimiSajam() throws SQLException {
        kreirajSajam.setSatnica(satnicaLista);
        kreirajSajam.setPaketi(paketLista);
        kreirajSajam.setDodatneUsluge(dodatneUslugeLista);

        int idSajam = SajamDAO.snimiSajamosnovniPodaci(kreirajSajam);

        kreirajSajam.setIdSajam(idSajam);

        for (int i = 0; i < kreirajSajam.getSatnica().size(); i++) {
            kreirajSajam.getSatnica().get(i).setIdSajam(kreirajSajam.getIdSajam());
            SajamDAO.snimiSatnicu(kreirajSajam.getSatnica().get(i));
        }
        for (int i = 0; i < kreirajSajam.getPaketi().size(); i++) {
            kreirajSajam.getPaketi().get(i).setIdSajam(kreirajSajam.getIdSajam());
            SajamDAO.snimiPaket(kreirajSajam.getPaketi().get(i));

        }

        for (DodatneUsluge du : kreirajSajam.getDodatneUsluge()) {
            du.setIdSajam(kreirajSajam.getIdSajam());
            SajamDAO.snimiDodatneUsluge(du);
        }

        return "/index-administrator?faces-redirect=true";
    }

    public String odustaniLink() {
        kreirajSajam = new Sajam();
        return "/index-administrator?faces-redirect=true";
    }

    public ControllerAdminKreirajSajam() {
    }

}
