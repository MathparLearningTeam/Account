package mathpar.web.learning.account.controllers;

import mathpar.web.learning.account.services.TokenService;
import mathpar.web.learning.account.utils.dto.responses.TokenResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static mathpar.web.learning.account.utils.AuthenticationUrls.*;
import static mathpar.web.learning.account.utils.TokenUtils.mapTokenToResponse;

@RestController
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping(GET_TOKEN_URL)
    public TokenResponse getToken(@RequestParam("token") String token){
        return mapTokenToResponse(tokenService.getToken(token));
    }

    @GetMapping(IS_TOKEN_VALID_URL)
    public boolean isTokenValid(@RequestParam("token") String token){
        return tokenService.getToken(token).getExpirationDate().after(new Date());
    }

    @DeleteMapping(PURGE_ALL_TOKENS_FOR_INSTITUTION)
    public void purgeAllTokens(@RequestParam("userIds") List<Long> userIds){
        tokenService.purgeAllTokensForUser(userIds);
    }
}
