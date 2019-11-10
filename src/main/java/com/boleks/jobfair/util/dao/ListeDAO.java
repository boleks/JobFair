
package com.boleks.jobfair.util.dao;

import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.Delatnost;
import com.boleks.jobfair.util.beans.Gradovi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ListeDAO {
    
    public static List<Gradovi> dohvatiGradove() throws SQLException{
        
        List<Gradovi> gradovi = new ArrayList<>();
        String sql = "SELECT * FROM grad";
        try(
                Connection c = DB.otvoriKonekciju();
                Statement s = c.createStatement();                                                             
                )
        {
            ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
               gradovi.add(new Gradovi(rs.getInt(1), rs.getString(2)));
            }
        }        
        return gradovi;                    
    }
    
        public static List<Delatnost> dohvatiDelatnost() throws SQLException{
        
        List <Delatnost> delatnosti = new ArrayList<>();
        String sql = "SELECT * FROM delatnost";
        try(
                Connection c = DB.otvoriKonekciju();
                Statement s = c.createStatement();                                                             
                )
        {
            ResultSet rs = s.executeQuery(sql);
            
            while (rs.next()) {
               delatnosti.add(new Delatnost(rs.getInt(1), rs.getString(2)));
            }
        }        
        return delatnosti;                    
    }
    
}
