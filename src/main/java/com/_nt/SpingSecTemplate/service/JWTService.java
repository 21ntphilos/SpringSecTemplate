package com._nt.SpingSecTemplate.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {


    private String secretKey = "CADCsavfeWebCE";
    private Map<String, Object> claims;

    public JWTService() {

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGen.generateKey();
            secretKey =  Base64.getEncoder().encodeToString(key.getEncoded()); // convert a byte array to a String

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 *60 *30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private Key getKey (){

        byte[] keybytes = Decoders.BASE64.decode(secretKey); // convert a string to a byte array
        return Keys.hmacShaKeyFor(keybytes);
    }
}
