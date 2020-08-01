package mathpar.web.learning.account._configs.security;

import mathpar.web.learning.account.entities.AuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserAuthentication implements Authentication {
    private final AuthenticationToken token;

    public UserAuthentication(AuthenticationToken token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ACCOUNT"));
    }

    @Override
    public String getCredentials() {
        return token.getToken();
    }

    @Override
    public AuthenticationToken getDetails() {
        return token;
    }

    @Override
    public Long getPrincipal() {
        return token.getAccountId();
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return token.getToken();
    }

}
