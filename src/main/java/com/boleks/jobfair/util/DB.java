package com.boleks.jobfair.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DB {
    
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String user = "root";
    public static String pass = "";

    public static String protocol = "jdbc:mysql:";
    public static String ip = "localhost";
    public static String port = "3306";
    public static String dbName = "jobfair";

    public static String connectionString
            = protocol + "//" + ip + ":" + port + "/" + dbName + "?useUnicode=yes&characterEncoding=UTF-8";       
    
    public static Connection otvoriKonekciju() throws SQLException {
        return DriverManager.getConnection(connectionString, user, pass);                
    }

}