package com.egs.bank.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
@Getter
@Setter
public class JwtToken {

    @Value("${jwt.key}")
    String jwtKey;

    String token;
    String cardNumber;
    String pin;

    public void detokenizeByCardNumber() {
        DecodedJWT decodedJWT = JWT.decode(token);
        cardNumber = decodedJWT.getClaim("cardNumber").asString();
    }

    public void detokenizeByCardNumberAndPin() {
        DecodedJWT decodedJWT = JWT.decode(token);
        cardNumber = decodedJWT.getClaim("cardNumber").asString();
        pin = decodedJWT.getClaim("pin").asString();
    }

    public boolean verify() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTDecodeException | SignatureVerificationException | NullPointerException e) {
            return false;
        }
    }

    public void generateToken(String jwtKey, String cardNumber, String pin) {
        Algorithm algorithmHS = Algorithm.HMAC256(jwtKey);
        token = JWT.create()
                .withClaim("cardNumber", cardNumber)
                .withClaim("pin", pin)
                .sign(algorithmHS);
    }
}
