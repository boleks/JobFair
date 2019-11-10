
package com.boleks.jobfair.util.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KompjuterskeVestine {
    
    private int idKV;
    private int idCV;
    private String program;
    private String nivo;
    
    
    //Konstruktori
    public KompjuterskeVestine() {
    }

    public KompjuterskeVestine(int idKV, int idCV, String program, String nivo) {
        this.idKV = idKV;
        this.idCV = idCV;
        this.program = program;
        this.nivo = nivo;
    }
    
        public KompjuterskeVestine(ResultSet rs) throws SQLException {
        this.idKV = rs.getInt("idKV");
        this.idCV = rs.getInt("idCV");
        this.program = rs.getString("Program");
        this.nivo = rs.getString("nivo");
    }
    
    
    //Geteri i seteri
    public int getIdKV() {
        return idKV;
    }

    public void setIdKV(int idKV) {
        this.idKV = idKV;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getNivo() {
        return nivo;
    }

    public void setNivo(String nivo) {
        this.nivo = nivo;
    }
    
    
            
    
}
