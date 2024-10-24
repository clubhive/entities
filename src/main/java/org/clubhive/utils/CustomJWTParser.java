package org.clubhive.utils;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import exceptions.InvalidTokenException;
import exceptions.NoBugsException;
import org.springframework.http.HttpStatus;

import java.text.ParseException;

public class CustomJWTParser {

    public static String parseSubjectJWT(String token) {

        try {
            JWT jwt = JWTParser.parse(token);
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            return claims.getSubject();
        } catch (ParseException e) {
            throw new NoBugsException("Invalid token", HttpStatus.UNAUTHORIZED);
        }
    }
}
