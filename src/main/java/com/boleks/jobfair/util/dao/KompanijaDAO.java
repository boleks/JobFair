package com.boleks.jobfair.util.dao;

import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.Kompanija;
import com.boleks.jobfair.util.beans.Konkurs;
import com.boleks.jobfair.util.beans.Korisnik;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KompanijaDAO {

    public static int registracijaKorisnik(Korisnik korisnik) throws SQLException {

        String sql = "insert into korisnik (korisnickoIme, lozinka, slika, tip) values(?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, korisnik.getKorisnickoIme());
            ps.setString(2, korisnik.getLozinka());
            ps.setString(3, korisnik.getSlika());
            ps.setString(4, String.valueOf(korisnik.getTip()));

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        }
    }

    public static boolean registracijaKompanija(Kompanija kompanija) throws SQLException {
        int idK = registracijaKorisnik(kompanija);

        if (idK <= 0) {
            return false;
        }

        String sql = "insert into kompanija (idKorisnik, naziv, grad, adresa, direktor, pib, brojZaposlenih, email, websajt, delatnost, uzaDelatnost) values (?,?,?,?,?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idK);
            ps.setString(2, kompanija.getNaziv());
            ps.setInt(3, kompanija.getGrad());
            ps.setString(4, kompanija.getAdresa());
            ps.setString(5, kompanija.getDirektor());
            ps.setInt(6, kompanija.getPib());
            ps.setInt(7, kompanija.getBrojZaposlenih());
            ps.setString(8, kompanija.getEmail());
            ps.setString(9, kompanija.getWebsajt());
            ps.setInt(10, kompanija.getDelatnost());
            ps.setString(11, kompanija.getUzaDelatnost());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false; //Treba dodati da se korisnik obrise ukoliko se ne kreira Kompanija
        }

    }

    public static Kompanija dohvatiKompanija(int idKorisnik) throws SQLException {
        Kompanija kompanija = new Kompanija();
        String sql = "SELECT * FROM korisnik natural join kompanija where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKorisnik);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                kompanija = new Kompanija(rs);
            }
        }
        return kompanija;

    }

    public static String dohvatiNaziv(int idKompanija) throws SQLException {
        String sql = "SELECT naziv FROM kompanija where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKompanija);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        }
        return "";

    }

    public static boolean izmeniPodatkeKompanija(Kompanija kompanija) throws SQLException {

        String sql = "update kompanija set naziv = ?, grad = ?, adresa = ?, direktor = ?, pib = ?, brojZaposlenih =?, email=?, websajt=?, delatnost=?, uzaDelatnost=? where idKorisnik = ?";
        String sqlKorisnik = "update korisnik set slika = ? where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                PreparedStatement psKorisnik = c.prepareStatement(sqlKorisnik);) {

            ps.setString(1, kompanija.getNaziv());
            ps.setInt(2, kompanija.getGrad());
            ps.setString(3, kompanija.getAdresa());
            ps.setString(4, kompanija.getDirektor());
            ps.setInt(5, kompanija.getPib());
            ps.setInt(6, kompanija.getBrojZaposlenih());
            ps.setString(7, kompanija.getEmail());
            ps.setString(8, kompanija.getWebsajt());
            ps.setInt(9, kompanija.getDelatnost());
            ps.setString(10, kompanija.getUzaDelatnost());
            ps.setInt(11, kompanija.getIdKorisnik());

            psKorisnik.setString(1, kompanija.getSlika());
            psKorisnik.setInt(2, kompanija.getIdKorisnik());

            if (ps.executeUpdate() > 0 && psKorisnik.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static List<Kompanija> pretragaKompanijaStudent(String naziv, int[] delatnost, int[] grad) throws SQLException {
        List<Kompanija> kompanijeRezultat = new ArrayList<>();

        String kompanija = "%" + naziv + "%";

        String sql = "select p.*, k.slika from korisnik k, kompanija p where p.idKorisnik = k.idKorisnik and p.naziv like ?";

        if (delatnost.length > 0) {
            for (int i = 0; i < delatnost.length; i++) {
                if (i == 0) {
                    sql += " and delatnost = " + delatnost[i];
                } else if (i < delatnost.length) {
                    sql += " or delatnost = " + delatnost[i];
                }
            }
        }

        if (grad.length > 0) {
            for (int i = 0; i < grad.length; i++) {
                if (i == 0) {
                    sql += " and grad = " + grad[i];
                } else if (i < grad.length) {
                    sql += " or grad = " + grad[i];
                }
            }
        }

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, kompanija);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kompanija k = new Kompanija();
                k.setIdKorisnik(rs.getInt(1));
                k.setNaziv(rs.getString(2));
                k.setGrad(rs.getInt(3));
                k.setAdresa(rs.getString(4));
                k.setDirektor(rs.getString(5));
                k.setPib(rs.getInt(6));
                k.setBrojZaposlenih(rs.getInt(7));
                k.setEmail(rs.getString(8));
                k.setWebsajt(rs.getString(9));
                k.setDelatnost(rs.getInt(10));
                k.setUzaDelatnost(rs.getString(11));
                k.setSlika(rs.getString(12));
                kompanijeRezultat.add(k);
            }

        }
        return kompanijeRezultat;
    }

    public static List<Konkurs> pretragaKonkursa(String nazivKompanije, String nazivKonkursa, char[] tip) throws SQLException {
        List<Konkurs> konkursiRezultat = new ArrayList<>();

        String kompanija = "%" + nazivKompanije + "%";
        String konkurs = "%" + nazivKonkursa + "%";

        String sql = "select k.*, p.naziv from konkurs k join kompanija p where k.idKompanija = p.idKorisnik and k.nazivPozicija like ? and p.naziv like ?";

        if (tip.length == 1) {
            sql += " and k.tip = '" + tip[0] + "'";
        }

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, konkurs);
            ps.setString(2, kompanija);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Konkurs k = new Konkurs();
                k.setIdKonkurs(rs.getInt(1));
                k.setIdKompanija(rs.getInt(2));
                k.setNazivPozicija(rs.getString(3));
                k.setTekstKonkursa(rs.getString(4));
                k.setRokZaPrijavu(rs.getDate(5));
                k.setPrilog(rs.getString(6));
                k.setTip(rs.getString(7).charAt(0));

                konkursiRezultat.add(k);
            }

        }
        return konkursiRezultat;
    }

    public static List<Kompanija> dohvatiUcesnike() throws SQLException {

        List<Kompanija> ucesnici = new ArrayList<>();

        String sql = "select p.*, k.slika from korisnik k, kompanija p where p.idKorisnik = k.idKorisnik";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kompanija k = new Kompanija();
                k.setIdKorisnik(rs.getInt(1));
                k.setNaziv(rs.getString(2));
                k.setGrad(rs.getInt(3));
                k.setAdresa(rs.getString(4));
                k.setDirektor(rs.getString(5));
                k.setPib(rs.getInt(6));
                k.setBrojZaposlenih(rs.getInt(7));
                k.setEmail(rs.getString(8));
                k.setWebsajt(rs.getString(9));
                k.setDelatnost(rs.getInt(10));
                k.setUzaDelatnost(rs.getString(11));
                k.setSlika(rs.getString(12));
                ucesnici.add(k);
            }

        }
        return ucesnici;

    }
}
