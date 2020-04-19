package mathpar.web.learning.account.services;

import mathpar.web.learning.account.entities.AuthenticationToken;
import mathpar.web.learning.account.repositories.ChangePasswordTokenRepository;
import mathpar.web.learning.account.repositories.AuthenticationTokenRepository;
import mathpar.web.learning.account.utils.TokenUtils;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TokenService {
    private final static Logger logger = Logger.getLogger("TokenService");
    private final AuthenticationTokenRepository authenticationTokenRepository;

    public TokenService(ChangePasswordTokenRepository changePasswordTokenRepository, AuthenticationTokenRepository authenticationTokenRepository) {
        this.authenticationTokenRepository = authenticationTokenRepository;
    }

    public AuthenticationToken getToken(String tokenStr){
        var token = authenticationTokenRepository.findById(tokenStr).orElseThrow(InvalidTokenException::new);
        if (token.getExpirationDate().before(new Date())){
            authenticationTokenRepository.delete(token);
            throw new InvalidTokenException("Token is expired");
        }
        return token;
    }

    public AuthenticationToken createToken(int callCount, long userId, String issuer){
        var token = new AuthenticationToken(userId, issuer);
        try {
            return authenticationTokenRepository.save(token);
        }catch (DuplicateKeyException exceptions){
            logger.warning(String.format("Can't create token %s because of duplicate exception", token.getToken()));
            if(TokenUtils.MAX_GENERATION_RETRY_ALLOWED<5) return createToken(callCount+1, userId, issuer);
            throw new RuntimeException(String.format("Can't create token after %d tries", TokenUtils.MAX_GENERATION_RETRY_ALLOWED));
        }
    }

    @Transactional
    public void purgeAllTokensForUser(List<Long> userIds){
        authenticationTokenRepository.deleteAllByAccountIdIn(userIds);
    }
}
