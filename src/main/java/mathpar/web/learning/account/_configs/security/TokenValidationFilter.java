package mathpar.web.learning.account._configs.security;

import mathpar.web.learning.account.services.TokenService;
import mathpar.web.learning.account.utils.Constants;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TokenValidationFilter implements Filter {

    private final TokenService tokenService;

    public TokenValidationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var tokenStr = ((HttpServletRequest) request).getHeader(Constants.TOKEN_HEADER_NAME);
        var context = SecurityContextHolder.getContext();
        if (tokenStr == null){
            context.setAuthentication(new AnonymousAuthenticationToken("anonymous", "anonymous", List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))));
        }
        else {
            try {
                var token = tokenService.getToken(tokenStr);
                context.setAuthentication(new UserAuthentication(token));
            } catch (InvalidTokenException e) {
                sendError(response, 403, "Token is invalid: " + e.getMessage());
                return;
            }
        }
        chain.doFilter(request, response);
    }

    private void sendError(ServletResponse response, int status, String message) throws IOException {
        ((HttpServletResponse)response).sendError(status, message);
    }
}
