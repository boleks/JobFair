
package com.boleks.jobfair.util.beans;

import java.time.LocalDate;

public class KonkursPrijava {
    private int idPrijava;
    private int idKonkurs;
    private int idStudent;
    private int idCV;
    private LocalDate datumPrijave;
    private int ocenaPrijave;
    private Character izabran;
    private LocalDate datumIzbora;
        

    public KonkursPrijava() {
    }

    public KonkursPrijava(int idPrijava, int idKonkurs, int idStudent, int idCV, LocalDate datumPrijave, int ocenaPrijave, Character izabran, LocalDate datumIzbora) {
        this.idPrijava = idPrijava;
        this.idKonkurs = idKonkurs;
        this.idStudent = idStudent;
        this.idCV = idCV;
        this.datumPrijave = datumPrijave;
        this.ocenaPrijave = ocenaPrijave;
        this.izabran = izabran;
        this.datumIzbora = datumIzbora;
    }

    public int getIdPrijava() {
        return idPrijava;
    }

    public void setIdPrijava(int idPrijava) {
        this.idPrijava = idPrijava;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public LocalDate getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(LocalDate datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public int getOcenaPrijave() {
        return ocenaPrijave;
    }

    public void setOcenaPrijave(int ocenaPrijave) {
        this.ocenaPrijave = ocenaPrijave;
    }

    public Character getIzabran() {
        return izabran;
    }

    public void setIzabran(Character izabran) {
        this.izabran = izabran;
    }

    public LocalDate getDatumIzbora() {
        return datumIzbora;
    }

    public void setDatumIzbora(LocalDate datumIzbora) {
        this.datumIzbora = datumIzbora;
    }

    
    

    public int getIdKonkurs() {
        return idKonkurs;
    }

    public void setIdKonkurs(int idKonkurs) {
        this.idKonkurs = idKonkurs;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    
}
