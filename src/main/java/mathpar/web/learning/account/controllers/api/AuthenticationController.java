package mathpar.web.learning.account.controllers.api;

import io.swagger.annotations.Api;
import mathpar.web.learning.account.services.AuthenticationService;
import mathpar.web.learning.account.services.TokenService;
import mathpar.web.learning.account.utils.PublicApi;
import mathpar.web.learning.account.utils.dto.payloads.AuthenticationPayload;
import mathpar.web.learning.account.utils.dto.responses.IsAuthenticatedResponse;
import mathpar.web.learning.account.utils.dto.responses.TokenResponse;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;
import org.springframework.web.bind.annotation.*;

import static mathpar.web.learning.account.utils.AuthenticationUrls.AUTHENTICATE_URL;
import static mathpar.web.learning.account.utils.AuthenticationUrls.IS_TOKEN_VALID;
import static mathpar.web.learning.account.utils.TokenUtils.mapTokenToResponse;

@RestController
@PublicApi
@Api(tags = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping(AUTHENTICATE_URL)
    public TokenResponse authenticateUser(@RequestBody AuthenticationPayload payload, @RequestHeader(value = "issuer", required = false, defaultValue = "Unknown") String issuer){
        return mapTokenToResponse(authenticationService.authenticate(payload.getEmail(), payload.getPassword(), issuer));
    }

    @GetMapping(IS_TOKEN_VALID)
    public IsAuthenticatedResponse isAuthenticated(@RequestParam("token") String token){
        //Expiration date and token validity has already been checked inside getToken method so it's just a matter of successful method execution
        try {
            return new IsAuthenticatedResponse(tokenService.getToken(token) != null);
        }catch (InvalidTokenException e){
            return new IsAuthenticatedResponse(false);
        }
    }
}
