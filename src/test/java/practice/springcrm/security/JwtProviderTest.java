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
}
