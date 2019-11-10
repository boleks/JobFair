package com.boleks.jobfair.controllers;

import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.beans.Paket;
import com.boleks.jobfair.util.beans.Satnica;
import com.boleks.jobfair.util.dao.KompanijaDAO;
import com.boleks.jobfair.util.dao.SajamDAO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named(value = "controllerPokrovitelji")
@SessionScoped
public class ControllerPokrovitelji implements Serializable {

    private final int idSajam;
    private final List<Paket> paketi;
    private final List<Kompanija> ucesnici;
    private final List<Satnica> satnica;

    public int getIdSajam() {
        return idSajam;
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public List<Kompanija> getUcesnici() {
        return ucesnici;
    }

    public List<Satnica> getSatnica() {
        return satnica;
    }
    
    public List<Kompanija> dohvatiKompanije (int idPaket) throws SQLException{
        return SajamDAO.dohvatiPokroviteljePoPaketima(idSajam, idPaket);
    }
    
    public String dohvatiImeKompanijeZaSatnicu(int idSatnica) throws SQLException{
       
        return SajamDAO.dohvatiImeKompanijeZaSatnicu(idSatnica);
    }
           
    public ControllerPokrovitelji() throws SQLException {
        this.idSajam = SajamDAO.dovatiIdAktivnogSajama();
        this.paketi = SajamDAO.dohvatiPaketeZaSajam(this.idSajam);
        this.ucesnici = KompanijaDAO.dohvatiUcesnike();
        this.satnica = SajamDAO.dohvatiRezervisaneSatniceSajma(this.idSajam);
    }
    
}
