package se.dsve.book_auth.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String extractUsername(String token) {
        // TODO: Write your code here
        return null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // TODO: Write your code here
        return null;
    }

    public String generateToken(UserDetails userDetails) {
        // TODO: Write your code here
        return null;
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        // TODO: Write your code here
        return null;
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // TODO: Write your code here
        return false;
    }

    private boolean isTokenExpired(String token) {
        // TODO: Write your code here
        return false;
    }

    private Date extractExpiration(String token) {
        // TODO: Write your code here
        return null;
    }

    private Claims extractAllClaims(String token) {
        // TODO: Write your code here
        return null;
    }

    private Key getSignInKey() {
        // TODO: Write your code here
        return null;
    }
}
