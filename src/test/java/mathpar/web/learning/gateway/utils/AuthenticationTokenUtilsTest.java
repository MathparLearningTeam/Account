package mathpar.web.learning.gateway.utils;

import mathpar.web.learning.account.entities.AuthenticationToken;
import mathpar.web.learning.account.utils.TokenUtils;
import mathpar.web.learning.account.utils.dto.responses.TokenResponse;
import org.junit.jupiter.api.Test;

import java.util.Date;

class AuthenticationTokenUtilsTest {

    @Test
    void mapTokenToResponse() {
        String token = "1_asdfgh", issuer = "issuer";
        Date expirationDate = new Date();
        TokenResponse expectedTokenResponse = new TokenResponse(token, expirationDate);
        AuthenticationToken authenticationTokenObject = new AuthenticationToken(token, 1, expirationDate, issuer);
        assert expectedTokenResponse.equals(TokenUtils.mapTokenToResponse(authenticationTokenObject));
    }
}