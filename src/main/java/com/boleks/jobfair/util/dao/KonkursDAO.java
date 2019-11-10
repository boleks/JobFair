package com.boleks.jobfair.util.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.Konkurs;
import com.boleks.jobfair.util.beans.KonkursPrijava;
import com.boleks.jobfair.util.beans.PropratnoPismo;


public class KonkursDAO {

    public static int napraviKonkurs(Konkurs konkurs) throws SQLException {

        String sql = "insert into konkurs (idKompanija, nazivPozicija, tekstKonkursa, rokZaPrijavu, prilog, tip) values(?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, konkurs.getIdKompanija());
            ps.setString(2, konkurs.getNazivPozicija());
            ps.setString(3, konkurs.getTekstKonkursa());
            ps.setDate(4, new Date(konkurs.getRokZaPrijavu().getTime()));
            ps.setString(5, konkurs.getPrilog());
            ps.setString(6, String.valueOf(konkurs.getTip()));
            
            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;
          
        }
    }

    public static int prijavaZaKonkurs(KonkursPrijava konkursPrijava) throws SQLException {

        String sql = "insert into konkurs_prijava (idKonkurs, idStudent, idCV, datumPrijave, ocenaPrijave, izabran, datumIzbora) values(?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, konkursPrijava.getIdKonkurs());
            ps.setInt(2, konkursPrijava.getIdStudent());
            ps.setInt(3, konkursPrijava.getIdCV());
            ps.setDate(4, Date.valueOf(konkursPrijava.getDatumPrijave()));
            ps.setInt(5, konkursPrijava.getOcenaPrijave());
            ps.setString(6, konkursPrijava.getIzabran().toString());
            ps.setDate(7, Date.valueOf(konkursPrijava.getDatumIzbora()));

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public static List<Konkurs> konkursiKompanije(int idKompanija) throws SQLException {
        List<Konkurs> konkursiKompanije = new ArrayList<>();
        String sql = "select * from konkurs where idKompanija = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idKompanija);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                konkursiKompanije.add(new Konkurs(rs));
            }
            return konkursiKompanije;
        }
    }

    public static Konkurs dohvatiKonkurs(int idKonkurs) throws SQLException {

        String sql = "select * from konkurs where idKonkurs = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idKonkurs);

            ResultSet rs = ps.executeQuery();
            rs.next();
            Konkurs konkurs = new Konkurs(rs);
            return konkurs;
        }
    }

    public static boolean studenKonkurisao(int idKonkurs, int idStudent) throws SQLException {

        String sql = "select * from konkurs_prijava where idKonkurs = ? and idStudent = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idKonkurs);
            ps.setInt(2, idStudent);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;

        }
    }

    public static int prijavaStudentaNaKonkurs(KonkursPrijava kp) throws SQLException {

        String sql = "insert into konkurs_prijava (idKonkurs, idStudent, idCV, datumPrijave, ocenaPrijave, izabran) values (?,?,?,?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, kp.getIdKonkurs());
            ps.setInt(2, kp.getIdStudent());
            ps.setInt(3, kp.getIdCV());
            ps.setDate(4, Date.valueOf(kp.getDatumPrijave()));
            ps.setInt(5, kp.getOcenaPrijave());
            ps.setString(6, kp.getIzabran().toString());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static int prijavaStudentaPropratnoPismo(PropratnoPismo pp) throws SQLException {

        String sql = "insert into propratno_pismo (idPrijava, propratnoPismo) values (?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, pp.getIdPrijave());
            ps.setString(2, pp.getPropratnoPismo());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static List<KonkursPrijava> dohvatiSvePrijaveStudenta(int idStudent) throws SQLException {
        List<KonkursPrijava> prijaveStudent = new ArrayList<>();
        String sql = "select * from konkurs_prijava where idStudent = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idStudent);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KonkursPrijava k = new KonkursPrijava();
                k.setIdPrijava(rs.getInt(1));
                k.setIdKonkurs(rs.getInt(2));
                k.setIdStudent(rs.getInt(3));
                k.setIdCV(rs.getInt(4));
                k.setDatumPrijave(rs.getDate(5).toLocalDate());
                k.setOcenaPrijave(rs.getInt(6));
                k.setIzabran(rs.getString(7).charAt(0));
                if (k.getIzabran() == 'D') {
                    k.setDatumIzbora(rs.getDate(8).toLocalDate());
                }
                prijaveStudent.add(k);
            }

        }
        return prijaveStudent;

    }

    public static List<KonkursPrijava> dohvatiSvePrijaveNaKonkurs(int idKonkurs) throws SQLException {
        List<KonkursPrijava> konkursSvePrijave = new ArrayList<>();
        String sql = "select * from konkurs_prijava where idKonkurs = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKonkurs);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                KonkursPrijava k = new KonkursPrijava();
                k.setIdPrijava(rs.getInt(1));
                k.setIdKonkurs(rs.getInt(2));
                k.setIdStudent(rs.getInt(3));
                k.setIdCV(rs.getInt(4));
                k.setDatumPrijave(rs.getDate(5).toLocalDate());
                k.setOcenaPrijave(rs.getInt(6));
                k.setIzabran(rs.getString(7).charAt(0));
                konkursSvePrijave.add(k);
            }

        }
        return konkursSvePrijave;

    }

    public static int dohvatiIdKompanije(int idKonkus) throws SQLException {

        String sql = "select idKompanija from konkurs where idKonkurs = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKonkus);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        }
        return 0;

    }

    public static boolean studentOcenioKompaniju(int idKompanija, int idStudent) throws SQLException {

        String sql = "select * from ocena_kompanije where idKompanija = ? and idStudent = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKompanija);
            ps.setInt(2, idStudent);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        }
        return false;

    }

    public static int brojPristiglihPrijava(int idKonkurs) throws SQLException {

        String sql = "select count(idKonkurs) from konkurs_prijava where idKonkurs = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idKonkurs);

            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);
        }

    }
    
    public static PropratnoPismo dohvatiPP(int idPrijava) throws SQLException {
        PropratnoPismo pp = new PropratnoPismo();
        String sql = "select * from propratno_pismo where idPrijava = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idPrijava);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                pp = new PropratnoPismo(rs);
            }           
            return pp;
        }

    }
    
    public static boolean oceniPrijavu(KonkursPrijava kp) throws SQLException {
        
        String sql = "update konkurs_prijava set ocenaPrijave = ? where idPrijava = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, kp.getOcenaPrijave());
            ps.setInt(2, kp.getIdPrijava());
                                  
            if(ps.executeUpdate()>0){
                return true;
            }           
            return false;
        }

    }
    
    public static boolean dodeliStatusPrijave(KonkursPrijava kp) throws SQLException {
        
        String sql = "update konkurs_prijava set izabran = ?, datumIzbora = ? where idPrijava = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, kp.getIzabran().toString());
            ps.setDate(2, Date.valueOf(kp.getDatumIzbora()));
            ps.setInt(3, kp.getIdPrijava());            
                                  
            if(ps.executeUpdate()>0){
                return true;
            }           
            return false;
        }

    }
}
