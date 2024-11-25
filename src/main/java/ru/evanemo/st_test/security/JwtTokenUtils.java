package ru.evanemo.st_test.security;


import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
    private final SecretKey secretKey;
    private final JwtParser jwtParser;

    public JwtTokenUtils(){
        var signatureAlgorithm = SignatureAlgorithm.HS256;
        String SECRET_KEY = "secretasdflksajdflkasjdflkjassadfasdjfaklsdjlfakdkjfalksdjfsjlklkfjaslfjsajjldsals";
        byte[] secretKeyBytes = Base64.getDecoder().decode(SECRET_KEY);
        this.secretKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        this.jwtParser = Jwts.parser().verifyWith(this.secretKey).build();
    }

    private Claims getAllClaimsFromToken(String token){
        return jwtParser.parseSignedClaims(token).getPayload();
    }
    public List<GrantedAuthority> getRoles(String token){
        //get roles as String values and then cast them into list of grantedAuthority
        var rolesStrings = ((List<String>)this.getAllClaimsFromToken(token).get("roles"));
        return rolesStrings.stream().map(RolesGrantedAuthorityAdapter::new).collect(Collectors.toList());
    }
    public UUID getUserId(String token){
        return UUID.fromString((String)getAllClaimsFromToken(token).get("id"));
    }
    public JwtTokenStatus isTokenValid(String token){
        try{
            getAllClaimsFromToken(token);
            return JwtTokenStatus.OK;
        }catch (ExpiredJwtException e){
            return JwtTokenStatus.EXPIRED;
        }catch (JwtException e){
            return JwtTokenStatus.WRONG;
        }
    }
}