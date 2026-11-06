package practice.springcrm.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import practice.springcrm.entity.Role;
import practice.springcrm.entity.User;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtProviderTest {
    private static final String KEY = Base64.getEncoder().encodeToString(
            "0123456789abcdef0123456789abcdef".getBytes(StandardCharsets.UTF_8));
    private final JwtProvider provider = new JwtProvider(KEY, 3_600_000);

    private User user() {
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("alice");
        when(user.getRole()).thenReturn(Role.ROLE_STUDENT);
        return user;
    }

    @Test
    void acceptsFreshSignedToken() {
        assertTrue(provider.validateToken(provider.generateToken(user())));
    }

    @Test
    void extractsUsername() {
        assertEquals("alice", provider.getUsernameFromToken(provider.generateToken(user())));
    }

    @Test
    void extractsRole() {
        assertEquals("ROLE_STUDENT", provider.getRoleFromToken(provider.generateToken(user())));
    }

    @Test
    void rejectsEmptyToken() {
        assertFalse(provider.validateToken(""));
    }

    @Test
    void rejectsMalformedToken() {
        assertFalse(provider.validateToken("not.a.jwt"));
    }

    @Test
    void rejectsExpiredToken() {
        JwtProvider expired = new JwtProvider(KEY, -60_000);
        assertFalse(expired.validateToken(expired.generateToken(user())));
    }

    @Test
    void rejectsDifferentIssuer() {
        String token = Jwts.builder().setSubject("alice").setIssuer("untrusted")
                .setExpiration(new Date(System.currentTimeMillis() + 60_000))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(KEY))).compact();
        assertFalse(provider.validateToken(token));
    }

    @Test
    void rejectsDifferentSigningKey() {
        String otherKey = Base64.getEncoder().encodeToString(
                "abcdef0123456789abcdef0123456789".getBytes(StandardCharsets.UTF_8));
        String token = new JwtProvider(otherKey, 60_000).generateToken(user());
        assertFalse(provider.validateToken(token));
    }

    @Test
    void rejectsAlteredPayload() {
        String token = provider.generateToken(user());
        String[] parts = token.split("\\.");
        parts[1] = (parts[1].startsWith("A") ? "B" : "A") + parts[1].substring(1);
        assertFalse(provider.validateToken(String.join(".", parts)));
    }
}
