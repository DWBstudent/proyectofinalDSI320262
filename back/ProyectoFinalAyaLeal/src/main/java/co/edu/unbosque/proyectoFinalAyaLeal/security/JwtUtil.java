package co.edu.unbosque.proyectoFinalAyaLeal.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import co.edu.unbosque.proyectoFinalAyaLeal.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


// extrae correo y rol
@Component
public class JwtUtil {
  private static final long VALIDEZTOKEN = 12 * 60 * 60 * 1000; // duran solo 12 horas

  // llave para firmar los tokes
  @Value("${jwt.secret}")
  private String secret;

  public JwtUtil() {} // constructor


  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

// extraccion de datos especificos
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }


  public String extractRol(String token) {
    return extractClaim(token, claims -> claims.get("rol", String.class));
  }


  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  
  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  // informar para el token expirado
  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

 // generacion de tokens y firmas 
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    if (userDetails instanceof Usuario usuario) {
      claims.put("rol", usuario.getRol().name());
    }
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + VALIDEZTOKEN))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {// validacion rapida
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }
}