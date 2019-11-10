package com.boleks.jobfair.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Neo
 */
public class Enkripcija {
    
    public static String enkripcijaLozinke(String lozinka) throws NoSuchAlgorithmException {
        String enkriptovanaLozinka = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(lozinka.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format

            enkriptovanaLozinka = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (enkriptovanaLozinka != null) {
            return enkriptovanaLozinka;
        }
        return lozinka;
    }
    
}
