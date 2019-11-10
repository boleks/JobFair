package com.boleks.jobfair.util.dao;

import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.Korisnik;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class KorisnikDAO {

    public static Korisnik dohvatiKorisnika(String korisnickoIme, String lozinka) throws SQLException {
        Korisnik korisnik = new Korisnik();
        String sql = "select * from korisnik where korisnickoIme = ? and lozinka = ?";

        try (
                
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, korisnickoIme);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                korisnik = new Korisnik(rs);
            }
        }
        return korisnik;
    }
    
    ////Bojan dodato////
    public static boolean proveraKorisnikPostoji(String korisnickoIme) throws SQLException{
        
        String sql = "select korisnickoIme from korisnik where korisnickoIme = ?";
        
        try(
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                )
        {
            ps.setString(1, korisnickoIme);
            
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }                                
    }
    
       public static boolean izmenaLozinke (int idKorisnik, String novaLozinka) throws SQLException{
        String sql = "update korisnik set lozinka = ? where idKorisnik = ?";
        
        try (                
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);                
                ){
            ps.setString(1, novaLozinka);
            ps.setInt(2, idKorisnik);            
            
            if(ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }                                    
        }
                
    }
    
    
}
