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
import com.boleks.jobfair.util.beans.CV;
import com.boleks.jobfair.util.beans.Jezik;
import com.boleks.jobfair.util.beans.KompjuterskeVestine;
import com.boleks.jobfair.util.beans.Obrazovanje;
import com.boleks.jobfair.util.beans.RadnoIskustvo;

public class StudentCvDAO {

    ///Kreiraj CV
    public static int snimiCVosnovniPodaci(CV cv) throws SQLException {

        String sql = "INSERT INTO cv (idKorisnik, ime, prezime, email, telefon, grad, ostaleVestine) VALUES (?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, cv.getIdKorisnik());
            ps.setString(2, cv.getIme());
            ps.setString(3, cv.getPrezime());
            ps.setString(4, cv.getEmail());
            ps.setString(5, cv.getTelefon());
            ps.setString(6, cv.getGrad());
            ps.setString(7, cv.getOstaleVestine());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;
        }
    }

    public static boolean snimiCVObrazovanje(Obrazovanje obrazovanje) throws SQLException {

        String sql = "INSERT INTO obrazovanje (idCV, datumOd, datumDo, nazivSkole, smer, grad, zemlja) VALUES (?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, obrazovanje.getIdCV());
            ps.setDate(2, new Date(obrazovanje.getDatumOd().getTime()));
            ps.setDate(3, new Date(obrazovanje.getDatumDo().getTime()));
            ps.setString(4, obrazovanje.getNazivSkole());
            ps.setString(5, obrazovanje.getSmer());
            ps.setString(6, obrazovanje.getGrad());
            ps.setString(7, obrazovanje.getZemlja());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
    }

    public static boolean snimiCVRadnoIskustvo(RadnoIskustvo radnoIskustvo) throws SQLException {

        String sql = "INSERT INTO radno_iskustvo (idCV, datumOd, datumDo, pozicija, kompanija, grad, zemlja, opisZaduzenja) VALUES (?,?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, radnoIskustvo.getIdCV());
            ps.setDate(2, new Date(radnoIskustvo.getDatumOd().getTime()));
            if (radnoIskustvo.getDatumDo() != null) {
                ps.setDate(3, new Date(radnoIskustvo.getDatumDo().getTime()));
            } else {
                ps.setDate(3, null);
            }
            ps.setString(4, radnoIskustvo.getPozicija());
            ps.setString(5, radnoIskustvo.getKompanija());
            ps.setString(6, radnoIskustvo.getGrad());
            ps.setString(7, radnoIskustvo.getZemlja());
            ps.setString(8, radnoIskustvo.getOpisZaduzenja());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }

    }

    public static boolean snimiCVJezik(Jezik jezik) throws SQLException {

        String sql = "insert into jezik (idCV, naziv, nivo) values(?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, jezik.getIdCV());
            ps.setString(2, jezik.getNaziv());
            ps.setString(3, jezik.getNivo());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
    }

    public static boolean snimiCVKompjuterskeVestine(KompjuterskeVestine kv) throws SQLException {

        String sql = "insert into kompjuterske_vestine (idCV, Program, nivo) values(?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, kv.getIdCV());
            ps.setString(2, kv.getProgram());
            ps.setString(3, kv.getNivo());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;
        }
    }

    public static boolean korisnikImaCV(int idKorisnik) throws SQLException {

        String sql = "SELECT * FROM cv where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKorisnik);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;

        }
    }

    ///Dohvati kreiran CV
    public static CV dohvatiCV(int idKorisnik) throws SQLException {

        CV cvKorisnika = dohvatiOsnovnePodatke(idKorisnik);
        cvKorisnika.setObrazivanje(dohvatiObrazovanje(cvKorisnika.getIdCV()));
        cvKorisnika.setRadnoIskustvo(dohvatiRadnoIskustvo(cvKorisnika.getIdCV()));
        cvKorisnika.setJezik(dohvatiJezik(cvKorisnika.getIdCV()));
        cvKorisnika.setKompjuterskeVestine(dohvatiKompjuterskeVestine(cvKorisnika.getIdCV()));

        return cvKorisnika;
    }

    public static CV dohvatiOsnovnePodatke(int idKorisnik) throws SQLException {
        CV cvKorisnik = new CV();
        cvKorisnik.setIdKorisnik(idKorisnik);

        String sql = "select * from cv where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cvKorisnik.getIdKorisnik());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cvKorisnik.setIdCV(rs.getInt("idCV"));
                cvKorisnik.setIme(rs.getString("ime"));
                cvKorisnik.setPrezime(rs.getString("prezime"));
                cvKorisnik.setEmail(rs.getString("email"));
                cvKorisnik.setTelefon(rs.getString("telefon"));
                cvKorisnik.setGrad(rs.getString("grad"));
                cvKorisnik.setOstaleVestine(rs.getString("ostaleVestine"));
            }
            return cvKorisnik;
        }
    }

    public static List<Obrazovanje> dohvatiObrazovanje(int idCV) throws SQLException {
        List<Obrazovanje> obrazovanjeLista = new ArrayList<>();

        String sql = "select * from obrazovanje where idCV = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idCV);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                obrazovanjeLista.add(new Obrazovanje(rs));
            }

            return obrazovanjeLista;
        }
    }

    public static List<RadnoIskustvo> dohvatiRadnoIskustvo(int idCV) throws SQLException {
        List<RadnoIskustvo> radnoIskustvoLista = new ArrayList<>();

        String sql = "select * from radno_iskustvo where idCV = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idCV);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                radnoIskustvoLista.add(new RadnoIskustvo(rs));
            }

            return radnoIskustvoLista;
        }
    }

    public static List<Jezik> dohvatiJezik(int idCV) throws SQLException {
        List<Jezik> jezikLista = new ArrayList<>();

        String sql = "select * from jezik where idCV = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idCV);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                jezikLista.add(new Jezik(rs));
            }

            return jezikLista;
        }
    }

    public static List<KompjuterskeVestine> dohvatiKompjuterskeVestine(int idCV) throws SQLException {
        List<KompjuterskeVestine> kompjuterskeVestineLista = new ArrayList<>();

        String sql = "select * from kompjuterske_vestine where idCV = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idCV);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                kompjuterskeVestineLista.add(new KompjuterskeVestine(rs));
            }

            return kompjuterskeVestineLista;
        }
    }

    ///Izmeni CV
    public static boolean izmeniOsnovnePodatke(CV cv) throws SQLException {

        String sql = "update cv set ime = ?, prezime = ?, email = ?, telefon = ?, grad = ? where idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, cv.getIme());
            ps.setString(2, cv.getPrezime());
            ps.setString(3, cv.getEmail());
            ps.setString(4, cv.getTelefon());
            ps.setString(5, cv.getGrad());
            ps.setInt(6, cv.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;

            }
            return false;
        }
    }

    public static boolean obrisiObrazovanjeIzListe(Obrazovanje ob) throws SQLException {

        String sql = "delete from obrazovanje WHERE idObrazovanje = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, ob.getIdObrazovanje());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static boolean izmeniObrazovanjeIzListe(Obrazovanje ob) throws SQLException {

        String sql = "update obrazovanje set datumOd = ?, datumDo = ?, nazivSkole = ?, smer = ?, grad = ?, zemlja = ? where idObrazovanje = ? and idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, new Date(ob.getDatumOd().getTime()));
            ps.setDate(2, new Date(ob.getDatumDo().getTime()));
            ps.setString(3, ob.getNazivSkole());
            ps.setString(4, ob.getSmer());
            ps.setString(5, ob.getGrad());
            ps.setString(6, ob.getZemlja());
            ps.setInt(7, ob.getIdObrazovanje());
            ps.setInt(8, ob.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static int dodajObrazovanjeUListe(Obrazovanje ob) throws SQLException {

        String sql = "INSERT INTO obrazovanje (idCV, datumOd, datumDo, nazivSkole, smer, grad, zemlja) VALUES (?,?,?,?,?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, ob.getIdCV());
            ps.setDate(2, new Date(ob.getDatumOd().getTime()));
            ps.setDate(3, new Date(ob.getDatumDo().getTime()));
            ps.setString(4, ob.getNazivSkole());
            ps.setString(5, ob.getSmer());
            ps.setString(6, ob.getGrad());
            ps.setString(7, ob.getZemlja());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static boolean obrisiRadnoIskustvoIzListe(RadnoIskustvo ri) throws SQLException {

        String sql = "delete from radno_iskustvo WHERE idRI = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, ri.getIdRI());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static boolean izmeniRadnoIskustvoIzListe(RadnoIskustvo ri) throws SQLException {

        String sql = "update radno_iskustvo set datumOd = ?, datumDo = ?, pozicija = ?, kompanija = ?, grad = ?, zemlja = ?, opisZaduzenja = ? where idRI = ? and idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, new Date(ri.getDatumOd().getTime()));
            ps.setDate(2, new Date(ri.getDatumDo().getTime()));
            ps.setString(3, ri.getPozicija());
            ps.setString(4, ri.getKompanija());
            ps.setString(5, ri.getGrad());
            ps.setString(6, ri.getZemlja());
            ps.setString(7, ri.getOpisZaduzenja());
            ps.setInt(8, ri.getIdRI());
            ps.setInt(9, ri.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static int dodajRadnoIskustvoUListe(RadnoIskustvo ri) throws SQLException {

        String sql = "INSERT INTO radno_iskustvo (idCV, datumOd, datumDo, pozicija, kompanija, grad, zemlja, opisZaduzenja) VALUES (?,?,?,?,?,?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, ri.getIdCV());
            ps.setDate(2, new Date(ri.getDatumOd().getTime()));
            if (ri.getDatumDo() != null) {
                ps.setDate(3, new Date(ri.getDatumDo().getTime()));
            } else {
                ps.setDate(3, null);
            }
            ps.setString(4, ri.getPozicija());
            ps.setString(5, ri.getKompanija());
            ps.setString(6, ri.getGrad());
            ps.setString(7, ri.getZemlja());
            ps.setString(8, ri.getOpisZaduzenja());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static boolean izmeniJezikIzListe(Jezik jezik) throws SQLException {

        String sql = "update jezik set naziv = ?, nivo = ? where idJezik = ? and idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, jezik.getNaziv());
            ps.setString(2, jezik.getNivo());
            ps.setInt(3, jezik.getIdJezik());
            ps.setInt(4, jezik.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static int dodajJezikUListe(Jezik jezik) throws SQLException {

        String sql = "INSERT INTO jezik (idCV, naziv, nivo) VALUES (?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, jezik.getIdCV());
            ps.setString(2, jezik.getNaziv());
            ps.setString(3, jezik.getNivo());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static boolean izmeniKompjuterskuVestinuIzListe(KompjuterskeVestine kv) throws SQLException {

        String sql = "update kompjuterske_vestine set Program = ?, nivo = ? where idKV = ? and idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, kv.getProgram());
            ps.setString(2, kv.getNivo());
            ps.setInt(3, kv.getIdKV());
            ps.setInt(4, kv.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static int dodajKompjuterskuVestinuUListe(KompjuterskeVestine kv) throws SQLException {

        String sql = "INSERT INTO kompjuterske_vestine (idCV, Program, nivo) VALUES (?,?,?)";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setInt(1, kv.getIdCV());
            ps.setString(2, kv.getProgram());
            ps.setString(3, kv.getNivo());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
            return 0;

        }
    }

    public static boolean obrisiJezikIzListe(Jezik jezik) throws SQLException {

        String sql = "delete from jezik WHERE idJezik = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, jezik.getIdJezik());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static boolean obrisiKompjuterskuVestinuIzListe(KompjuterskeVestine kv) throws SQLException {

        String sql = "delete from kompjuterske_vestine WHERE idKV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, kv.getIdKV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static boolean izmeniOstaleVestine(CV cv) throws SQLException {

        String sql = "update cv set ostaleVestine = ? where idCV = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, cv.getOstaleVestine());
            ps.setInt(2, cv.getIdCV());

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false;

        }
    }

    public static int dohvatiIdCV(int idStudent) throws SQLException {

        String sql = "select idCV from cv where idKorisnik = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idStudent);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 0;

        }

    }
}
