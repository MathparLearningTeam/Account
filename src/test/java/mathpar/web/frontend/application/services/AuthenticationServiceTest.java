package mathpar.web.frontend.application.services;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.entities.AuthenticationToken;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.services.AuthenticationService;
import mathpar.web.learning.account.services.TokenService;
import mathpar.web.learning.account.utils.EncryptionUtils;
import mathpar.web.learning.account.utils.exceptions.NotAuthenticatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthenticationServiceTest {
    private final TokenService tokenService = Mockito.mock(TokenService.class);
    private final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    private final String issuer = "issuer";
    private final String email = "email";
    private final String password = "password";
    private final long userId = 1;

    private final AuthenticationToken generatedAuthenticationToken = new AuthenticationToken(userId, issuer);
    private final Account accountForAuthentication = new Account(userId, email, EncryptionUtils.createHash(password), null, null, null);

    private final AuthenticationService authenticationService = new AuthenticationService(accountRepository, tokenService);

    @BeforeEach
    void setUp() {
        Mockito.when(tokenService.createToken(0, userId, issuer)).thenReturn(generatedAuthenticationToken);
        Mockito.when(accountRepository.findByEmailAndPassword(email, EncryptionUtils.createHash(password))).thenReturn(Optional.of(accountForAuthentication));
    }

    @Test
    void authenticateExistingSuccessful() {
        assert generatedAuthenticationToken.equals(authenticationService.authenticate(email, password, issuer));
    }


    @Test
    void authenticateInvalidUser() {
        assertThrows(NotAuthenticatedException.class, () -> authenticationService.authenticate("InvalidEmail", "InvalidPassword", issuer));
    }

    @Test
    void authenticateInvalidPassword() {
        assertThrows(NotAuthenticatedException.class, () -> authenticationService.authenticate(email, "InvalidPassword", issuer));
    }
}