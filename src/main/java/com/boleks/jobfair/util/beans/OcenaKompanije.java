package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OcenaKompanije {

    private int idOcena;
    private int idKompanija;
    private int idStudent;
    private int ocena;
    private LocalDate datum;

    public OcenaKompanije() {
    }

    public OcenaKompanije(int idOcena, int idKompanija, int idStudent, int ocena, LocalDate datum) {
        this.idOcena = idOcena;
        this.idKompanija = idKompanija;
        this.idStudent = idStudent;
        this.ocena = ocena;
        this.datum = datum;
    }

    public OcenaKompanije(ResultSet rs) throws SQLException {
        this.idOcena = rs.getInt("idOcena");
        this.idKompanija = rs.getInt("idKompanija");
        this.idStudent = rs.getInt("idStudent");
        this.ocena = rs.getInt("ocena");
        this.datum = rs.getDate("datum").toLocalDate();
    }

    public int getIdOcena() {
        return idOcena;
    }

    public void setIdOcena(int idOcena) {
        this.idOcena = idOcena;
    }

    public int getIdKompanija() {
        return idKompanija;
    }

    public void setIdKompanija(int idKompanija) {
        this.idKompanija = idKompanija;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

}
