package mathpar.web.learning.account.utils;

import mathpar.web.learning.account.entities.AuthenticationToken;
import mathpar.web.learning.account.utils.dto.responses.TokenResponse;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;

public class TokenUtils {
    public final static int MAX_GENERATION_RETRY_ALLOWED = 5;
    public final static int hashSize = 25;

    public static String createToken(long userId){
        return String.join("_",String.valueOf(userId), EncryptionUtils.generateString(hashSize));
    }

    public static TokenResponse mapTokenToResponse(AuthenticationToken authenticationToken){
        return new TokenResponse(authenticationToken.getToken(), authenticationToken.getExpirationDate());
    }

    public static long mapTokenToId(String token){
        try {
            return Long.parseLong(token.substring(0, token.indexOf("_")));
        }catch (Exception e){
            throw new InvalidTokenException("Token is invalid");
        }
    }
}
