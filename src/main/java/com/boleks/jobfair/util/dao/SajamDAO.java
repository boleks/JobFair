package com.boleks.jobfair.util.dao;

import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SajamDAO {

    //kreiraj Sajam -upis u bazu//
    public static int snimiSajamosnovniPodaci(Sajam sajam) throws SQLException {
        String sql = "insert into sajam (naziv, datumOd, datumDo, osnovneInformacije, logo, datumStudent, datumKompanija) VALUES (?,?,?,?,?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, sajam.getNaziv());
            ps.setDate(2, new Date(sajam.getDatumOd().getTime()));
            ps.setDate(3, new Date(sajam.getDatumDo().getTime()));
            ps.setString(4, sajam.getOsnovneInformacije());
            ps.setString(5, sajam.getLogo());
            ps.setDate(6, new Date(sajam.getDatumStudent().getTime()));
            ps.setDate(7, new Date(sajam.getDatumKompanija().getTime()));

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;
        }

    }

    public static boolean snimiSatnicu(Satnica satnica) throws SQLException {
        String sql = "insert into satnica_sajam (idSajam, datum, vreme, tip, mestoOdrzavanja, status) VALUES (?,?,?,?,?,'Slobodna')";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, satnica.getIdSajam());
            ps.setDate(2, new Date(satnica.getDatum().getTime()));
            ps.setString(3, satnica.getVreme());
            ps.setString(4, satnica.getTip());
            ps.setString(5, satnica.getMestoOdrzavanja());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }

    }

    public static boolean snimiPaket(Paket paket) throws SQLException {
        String sql = "insert into paket (idSajam, naziv, sadrzaj, maxBroj, cena, brojRadionica, brojPredavanja) VALUES (?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, paket.getIdSajam());
            ps.setString(2, paket.getNaziv());
            ps.setString(3, paket.getSadrzaj());
            ps.setInt(4, paket.getMaxBroj());
            ps.setInt(5, paket.getCena());
            ps.setInt(6, paket.getBrojRadionica());
            ps.setInt(7, paket.getBrojPredavanja());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }

    }

    public static boolean snimiDodatneUsluge(DodatneUsluge dodatneusluge) throws SQLException {
        String sql = "insert into dodatne_usluge (idSajam, naziv, opis, cena) VALUES (?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, dodatneusluge.getIdSajam());
            ps.setString(2, dodatneusluge.getNaziv());
            ps.setString(3, dodatneusluge.getOpis());
            ps.setString(4, dodatneusluge.getCena());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
    }

    public static SajamPrijava dohvatiPrijavuNaSajam(Integer idSajam, Integer idKompanija) throws SQLException {

        String sql = "select * from prijava_sajam where  idSajam = ? and idKompanija = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);
            ps.setInt(2, idKompanija);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SajamPrijava(rs);
            } else {
                return null;
            }

        }
    }

    public static Integer snimiPrijavuNaSajam(SajamPrijava sajamPrijava) throws SQLException {

        String sql = "insert into prijava_sajam (idSajam, idPaket, idKompanija, status) VALUES (?,?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, sajamPrijava.getIdSajam());
            ps.setInt(2, sajamPrijava.getIdPaket());
            ps.setInt(3, sajamPrijava.getIdKompanija());
            ps.setString(4, sajamPrijava.getStatus());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return null;
            }
        }
    }

    public static List<Paket> dohvatiPaketeZaSajam(Integer selektovanSajamId) throws SQLException {
        List<Paket> paketi = new ArrayList<>();
        String sql = "select * from paket where idSajam = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, selektovanSajamId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                paketi.add(new Paket(rs));
            }
        }
        return paketi;
    }

    public static List<DodatneUsluge> dohvatiDodatneUslugeZaSajam(Integer selektovanSajamId) {
        List<DodatneUsluge> dodatneUslugeLista = new ArrayList<>();
        String sql = "select * from dodatne_usluge where idSajam = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, selektovanSajamId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dodatneUslugeLista.add(new DodatneUsluge(rs));
            }
        } catch (SQLException sQLException) {
        }
        return dodatneUslugeLista;
    }

    public static List<Sajam> dohvatiAktivneSajmove(LocalDate now) {
        List<Sajam> sajmovi = new ArrayList<>();
        String sql = "select * from sajam where datumKompanija >= ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, Date.valueOf(now));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sajmovi.add(new Sajam(rs));
            }
        } catch (SQLException sQLException) {
        }
        return sajmovi;
    }

    public static void snimiDodatnuUsluguNaPrijavu(Integer prijavaId, Integer dodatnaUslugaId) {
        String sql = "insert into prijava_dodatak (idPrijava, idDodatak) VALUES (?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, prijavaId);
            ps.setInt(2, dodatnaUslugaId);
            ps.executeUpdate();
        } catch (SQLException sQLException) {
        }
    }

    public static List<SajamPrijava> dohvatiSvePrijave(int idSajam) throws SQLException {
        List<SajamPrijava> svePrijave = new ArrayList<>();
        String sql = "select * from prijava_sajam where idSajam = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SajamPrijava sp = new SajamPrijava(rs);
                svePrijave.add(sp);
            }
        }
        return svePrijave;
    }

    public static List<DodatneUsluge> dohvatiDodatneUslugePrijave(int idPrijava) throws SQLException {
        List<DodatneUsluge> dodatneUslugeLista = new ArrayList<>();
        String sql = "select * from dodatne_usluge natural join prijava_dodatak where idPrijava = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idPrijava);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                dodatneUslugeLista.add(new DodatneUsluge(rs));
            }
        }
        return dodatneUslugeLista;
    }

    public static DodatneUsluge dohvatiDodatnuUslugu(int idDodatneUsluge) throws SQLException {

        String sql = "select * from dodatne_usluge where idDodatak = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idDodatneUsluge);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                DodatneUsluge du = new DodatneUsluge(rs);
                return du;
            }

        }
        return null;
    }

    public static Paket dohvatiPaketZaPrijavu(int idPaket) throws SQLException {

        String sql = "select * from paket where idPaket = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idPaket);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Paket p = (new Paket(rs));
                return p;
            }
        }
        return null;
    }

    public static void izmeniStatusPrijave(SajamPrijava sp) throws SQLException {

        String sql = "update prijava_sajam set status = ? where idPrijava = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, sp.getStatus());
            ps.setInt(2, sp.getIdPrijava());

            ps.executeUpdate();

        }
    }
    
    public static void izmeniNapomenu(SajamPrijava sp) throws SQLException {

        String sql = "update prijava_sajam set napomena = ? where idPrijava = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, sp.getNapomena());
            ps.setInt(2, sp.getIdPrijava());

            ps.executeUpdate();

        }
    }

    public static int brojOdobrenihPrijava(int idPaket) throws SQLException {

        String sql = "select count(*) from prijava_sajam where idPaket = ?  and status = 'Odobrena'";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idPaket);

            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getInt(1);

        }
    }

    public static boolean kompanijaImaPrijavu(int idKompanija) throws SQLException {

        String sql = "SELECT * FROM prijava_sajam where idKompanija = ? and status like 'Odobrena' or status like 'Cekanje'";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKompanija);

            return ps.execute();
        }
    }

    public static List<Satnica> dohvatiDostupneSatnice(String tip) throws SQLException {
        List<Satnica> dostupneSatnice = new ArrayList<>();
        String sql = "select * from satnica_sajam where tip like ? and status like 'Slobodna'";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, tip);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                dostupneSatnice.add(new Satnica(rs));
            }
            return dostupneSatnice;
        }

    }
    
    

    public static boolean rezervisiSatnicu(Satnica satnica, int idKompanija) throws SQLException {

        String sql = "update satnica_sajam set status = ? where idSatnica = ?";
        String sqlKompanija = "insert into satnica_kompanija (idSatnica, idKompanija) values (?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                PreparedStatement psKompanija = c.prepareStatement(sqlKompanija);) {

            ps.setString(1, satnica.getStatus());
            ps.setInt(2, satnica.getIdSatnica());
            psKompanija.setInt(1, satnica.getIdSatnica());
            psKompanija.setInt(2, idKompanija);

            if (ps.executeUpdate() > 0) {
                if (psKompanija.executeUpdate() > 0);
                return true;
            }
            return false;
        }
    }

    public static int dovatiIdAktivnogSajama() throws SQLException {
        Date datum = Date.valueOf(LocalDate.now());
        String sql = "select * from sajam where datumDo >= ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, datum);
            ResultSet rs = ps.executeQuery();

            if (rs.last()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }
    
    public static List<Sajam> dohvatiSajmove() throws SQLException {
        
        List<Sajam> sajmovi = new ArrayList<>();
        String sql = "select * from sajam";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sajmovi.add(new Sajam(rs));
            }
        }
        
        return sajmovi;
    }
    
    public static List<SajamPrijava> dohvatiSveOdobrenePrijave(int idSajam) throws SQLException {
        List<SajamPrijava> svePrijave = new ArrayList<>();
        String sql = "select * from prijava_sajam where idSajam = ? and status = 'Odobrena'";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SajamPrijava sp = new SajamPrijava(rs);
                svePrijave.add(sp);
            }
        }
        return svePrijave;
    }
    
    public static List<Satnica> dohvatiSatniceSajma(int idSajam) throws SQLException {
        List<Satnica> satniceSajma = new ArrayList<>();
        String sql = "select * from satnica_sajam where idSajam = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                satniceSajma.add(new Satnica(rs));
            }
            return satniceSajma;
        }

    }
    
    public static List<Satnica> dohvatiRezervisaneSatniceSajma(int idSajam) throws SQLException {
        List<Satnica> satniceSajma = new ArrayList<>();
        String sql = "select * from satnica_sajam where idSajam = ? and status = 'Rezevisana'";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                satniceSajma.add(new Satnica(rs));
            }
            return satniceSajma;
        }

    }
    
    public static String dohvatiImeKompanijeZaSatnicu(int idSatnica) throws SQLException{
        String sql = "select k.naziv from satnica_kompanija s, kompanija k where s.idKompanija = k.idKorisnik and s.idSatnica = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSatnica);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
            return "";
        }    
    }
    
    public static void izmeniSatnicu (Satnica s) throws SQLException{
        
        String sql = "update satnica_sajam set tip =?, mestoOdrzavanja = ? where idSatnica = ?";
        
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, s.getTip());
            ps.setString(2, s.getMestoOdrzavanja());
            ps.setInt(3, s.getIdSatnica());

            ps.executeUpdate();
            
        }
    }
    
    public static List<SajamPrijava> dohvatiSvePrijaveKompanije(int idKompanija) throws SQLException {
        List<SajamPrijava> svePrijave = new ArrayList<>();
        String sql = "select * from prijava_sajam where idKompanija = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKompanija);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SajamPrijava sp = new SajamPrijava(rs);
                svePrijave.add(sp);
            }
        }
        return svePrijave;
    }
    
    public static String dohvatiNazivSajma(int idSajam) throws SQLException {
       
        String sql = "select naziv from sajam where idSajam = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);

            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);
        }        
    }
    
    public static List<Kompanija> dohvatiPokroviteljePoPaketima (int idSajam, int idPaket) throws SQLException{
        List<Kompanija> kompanijePokrovitelji = new ArrayList<>();
        
        String sql = "select idKompanija from prijava_sajam where idSajam = ? and idPaket = ? and status = 'Odobrena'";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idSajam);
            ps.setInt(2, idPaket);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Kompanija k = KompanijaDAO.dohvatiKompanija(rs.getInt("idKompanija"));
                kompanijePokrovitelji.add(k);
            }
        }
        return kompanijePokrovitelji;
    }         

}
