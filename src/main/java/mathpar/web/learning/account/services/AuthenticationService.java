package mathpar.web.learning.account.services;

import mathpar.web.learning.account.entities.AuthenticationToken;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.utils.EncryptionUtils;
import mathpar.web.learning.account.utils.exceptions.NotAuthenticatedException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthenticationService {
    private final Logger logger = Logger.getLogger(AuthenticationService.class.getName());

    private final AccountRepository accountRepository;
    private final TokenService tokenService;

    public AuthenticationService(AccountRepository accountRepository, TokenService tokenService) {
        this.accountRepository = accountRepository;
        this.tokenService = tokenService;
    }

    public AuthenticationToken authenticate(String email, String password, String issuer){
        var account = accountRepository.findByEmailAndPassword(email, EncryptionUtils.createHash(password))
                .orElseThrow(NotAuthenticatedException::new);
        return tokenService.createToken(0, account.getId(), issuer);
    }
}
