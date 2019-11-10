package com.boleks.jobfair.util.dao;

import com.boleks.jobfair.util.DB;
import com.boleks.jobfair.util.beans.Korisnik;
import com.boleks.jobfair.util.beans.Student;

import java.sql.*;

public class StudentDAO {

    public static Student dohvatiStudenta(int idKorisnik) throws SQLException {
        Student student = new Student();
        String sql = "SELECT * FROM korisnik natural join student where idKorisnik = ?";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, idKorisnik);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                student = new Student(rs);
            }
        }
        return student;

    }

    ///Bojan dodato///
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

    public static boolean registracijaStudent(Student student) throws SQLException {
        int idK = registracijaKorisnik(student);

        if (idK <= 0) {
            return false;
        }

        String sql = "insert into student (idKorisnik, ime, prezime, telefon, email, godinaStudija, diplomirao) values (?,?,?,?,?,?,?)";

        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, idK);
            ps.setString(2, student.getIme());
            ps.setString(3, student.getPrezime());
            ps.setString(4, student.getTelefon());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getGodinaStudija());
            ps.setString(7, String.valueOf(student.getDiplomirao()));

            if (ps.executeUpdate() > 0) {
                return true;
            }
            return false; //Treba dodati da se korisnik obrise ukoliko se ne kreira Student
        }

    }

    public static boolean izmeniPodatkeStudent(Student student) throws SQLException {

        String sqlStudent = "update student set ime = ?, prezime = ?, telefon = ?, email = ?, godinaStudija = ?, diplomirao = ? where idKorisnik = ?";
        String sqlKorisnik = "update korisnik set slika = ? where idKorisnik = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sqlStudent);
                PreparedStatement psKorisnik = c.prepareStatement(sqlKorisnik);) {

            ps.setString(1, student.getIme());
            ps.setString(2, student.getPrezime());
            ps.setString(3, student.getTelefon());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getGodinaStudija());
            ps.setString(6, String.valueOf(student.getDiplomirao()));
            ps.setInt(7, student.getIdKorisnik());
            psKorisnik.setString(1, student.getSlika());
            psKorisnik.setInt(2, student.getIdKorisnik());

            if (ps.executeUpdate() > 0 && psKorisnik.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public static String imePrezimeStudent(int idStudent) throws SQLException {

        String sql = "select ime, prezime from student where idKorisnik = ?";
        try (
                Connection c = DB.otvoriKonekciju();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {

            ps.setInt(1, idStudent);            
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {                
                return rs.getString("ime") + " " + rs.getString("prezime");
            } else {
                return "";
            }
        }
    }
}
