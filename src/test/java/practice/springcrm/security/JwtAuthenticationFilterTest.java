package practice.springcrm.security;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import practice.springcrm.entity.Role;
import practice.springcrm.entity.User;
import practice.springcrm.repository.UserRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {
    private final JwtProvider provider = mock(JwtProvider.class);
    private final UserRepo userRepo = mock(UserRepo.class);
    private final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(provider, userRepo);

    @AfterEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    private void execute(String authorization) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/courses/teacher/1");
        if (authorization != null) request.addHeader("Authorization", authorization);
        filter.doFilter(request, new MockHttpServletResponse(), new MockFilterChain());
    }

    private void validClaims() {
        when(provider.validateToken("valid")).thenReturn(true);
        when(provider.getUsernameFromToken("valid")).thenReturn("alice");
        when(provider.getRoleFromToken("valid")).thenReturn("ROLE_STUDENT");
    }

    private User persistedUser(Role role) {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(role);
        return user;
    }

    @Test
    void skipsAbsentAuthorization() throws Exception {
        execute(null);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verifyNoInteractions(provider, userRepo);
    }
}
