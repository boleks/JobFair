package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Paket {

    private int idPaket;
    private int idSajam;
    private String naziv;
    private String sadrzaj;
    private Integer maxBroj;
    private Integer cena;
    private Integer brojRadionica;
    private Integer brojPredavanja;

    public Paket() {
    }

    public Paket(int idPaket, int idSajam, String naziv, String sadrzaj, int maxBroj, int cena, int brojRadionica, int brojPredavanja) {
        this.idPaket = idPaket;
        this.idSajam = idSajam;
        this.naziv = naziv;
        this.sadrzaj = sadrzaj;
        this.maxBroj = maxBroj;
        this.cena = cena;
        this.brojRadionica = brojRadionica;
        this.brojPredavanja = brojPredavanja;
    }

    public Paket(ResultSet rs) throws SQLException {
        this.idPaket = rs.getInt("idPaket");
        this.idSajam = rs.getInt("idSajam");
        this.naziv = rs.getString("naziv");
        this.sadrzaj = rs.getString("sadrzaj");
        this.maxBroj = rs.getInt("maxBroj");
        this.cena = rs.getInt("cena");
        this.brojRadionica = rs.getInt("brojRadionica");
        this.brojPredavanja = rs.getInt("brojPredavanja");

    }

    public int getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }

    public int getIdSajam() {
        return idSajam;
    }

    public void setIdSajam(int idSajam) {
        this.idSajam = idSajam;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public Integer getMaxBroj() {
        return maxBroj;
    }

    public void setMaxBroj(Integer maxBroj) {
        this.maxBroj = maxBroj;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getBrojRadionica() {
        return brojRadionica;
    }

    public void setBrojRadionica(Integer brojRadionica) {
        this.brojRadionica = brojRadionica;
    }

    public Integer getBrojPredavanja() {
        return brojPredavanja;
    }

    public void setBrojPredavanja(Integer brojPredavanja) {
        this.brojPredavanja = brojPredavanja;
    }

}
