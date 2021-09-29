package com.ikubinfo.assignment.service;

import com.ikubinfo.assignment.dto.CustomException;
import com.ikubinfo.assignment.util.Constant;
import com.ikubinfo.assignment.util.Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by sabbir on 9/29/21.
 */

@Service
public class TokenService {

    public String createToken(String id, long time) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Utils.getTokenSecretKey(Constant.SECRET_KEY));
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(null)
                .setIssuer(null)
                .signWith(signatureAlgorithm, signingKey);

        if (time >= 0) {
            long expMillis = nowMillis + time;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public Claims parseToken(String accessToken) throws CustomException {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(Utils.getTokenSecretKey(Constant.SECRET_KEY)))
                    .parseClaimsJws(accessToken).getBody();

        } catch (Exception e){
            throw new CustomException(Constant.BAD_REQUEST, Constant.INVALID_TOKEN);
        }
    }
}
