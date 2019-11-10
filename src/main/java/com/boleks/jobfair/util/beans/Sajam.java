
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class Sajam {

    private int idSajam;
    private String naziv;
    private Date datumOd;
    private Date datumDo;
    private String osnovneInformacije;
    private String logo;
    private Date datumStudent;
    private Date datumKompanija;
    private List<Paket> paketi;
    private List<DodatneUsluge> dodatneUsluge;
    private List<Satnica> satnica;

    public Sajam() {
    }

    public Sajam(int idSajam, String naziv, Date datumOd, Date datumDo, String osnovneInformacije, String logo, Date datumStudent, Date datumKompanija, List<Paket> paketi, List<DodatneUsluge> dodatneUsluge, List<Satnica> satnica) {
        this.idSajam = idSajam;
        this.naziv = naziv;
        this.datumOd = datumOd;
        this.datumDo = datumDo;
        this.osnovneInformacije = osnovneInformacije;
        this.logo = logo;
        this.datumStudent = datumStudent;
        this.datumKompanija = datumKompanija;
        this.paketi = paketi;
        this.dodatneUsluge = dodatneUsluge;
        this.satnica = satnica;
    }

    public Sajam(ResultSet rs) throws SQLException {
        this.idSajam = rs.getInt("idSajam");
        this.naziv = rs.getString("naziv");
        this.datumOd = rs.getDate("datumOd");
        this.datumDo = rs.getDate("datumDo");
        this.osnovneInformacije = rs.getString("osnovneInformacije");
        this.logo = rs.getString("logo");
        this.datumStudent = rs.getDate("datumStudent");
        this.datumKompanija = rs.getDate("datumKompanija");
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

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public String getOsnovneInformacije() {
        return osnovneInformacije;
    }

    public void setOsnovneInformacije(String osnovneInformacije) {
        this.osnovneInformacije = osnovneInformacije;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getDatumStudent() {
        return datumStudent;
    }

    public void setDatumStudent(Date datumStudent) {
        this.datumStudent = datumStudent;
    }

    public Date getDatumKompanija() {
        return datumKompanija;
    }

    public void setDatumKompanija(Date datumKompanija) {
        this.datumKompanija = datumKompanija;
    }

    public List<Paket> getPaketi() {
        return paketi;
    }

    public void setPaketi(List<Paket> paketi) {
        this.paketi = paketi;
    }

    public List<DodatneUsluge> getDodatneUsluge() {
        return dodatneUsluge;
    }

    public void setDodatneUsluge(List<DodatneUsluge> dodatneUsluge) {
        this.dodatneUsluge = dodatneUsluge;
    }

    public List<Satnica> getSatnica() {
        return satnica;
    }

    public void setSatnica(List<Satnica> satnica) {
        this.satnica = satnica;
    }

}
