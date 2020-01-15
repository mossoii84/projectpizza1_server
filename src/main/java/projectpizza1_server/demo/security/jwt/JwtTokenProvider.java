package projectpizza1_server.demo.security.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import projectpizza1_server.demo.security.model.UserPrincipal;
import java.util.Date;


@Component
public class JwtTokenProvider {

    @Value("${uploadfile.app.jwtSecret}")
    private String secretKey;

    @Value("${uploadfile.app.jwtExpiration}")
    private int expirationTime;

    public String generateJwtToken(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime*1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public boolean isJwtTokenValid(String token) {
        try {

            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsernameFromJwtToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }
}
