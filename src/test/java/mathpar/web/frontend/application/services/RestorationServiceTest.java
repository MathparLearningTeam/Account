package mathpar.web.frontend.application.services;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.entities.ChangePasswordToken;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.repositories.ChangePasswordTokenRepository;
import mathpar.web.learning.account.services.mailing.MailerService;
import mathpar.web.learning.account.services.RestorationService;
import mathpar.web.learning.account.utils.EncryptionUtils;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RestorationServiceTest {
    private AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    private ChangePasswordTokenRepository tokensRepository = Mockito.mock(ChangePasswordTokenRepository.class);
    private MailerService mailerService = Mockito.mock(MailerService.class);

    private final RestorationService restorationService = new RestorationService(accountRepository, tokensRepository, mailerService);
    private ArgumentCaptor<ChangePasswordToken> captor = ArgumentCaptor.forClass(ChangePasswordToken.class);
    private ArgumentCaptor<String> credentialCaptor = ArgumentCaptor.forClass(String.class);

    private long userId = 1;
    private String email = "testEmail", token = "Token", newPassword = "newPassword", tokenExpired = "expired", tokenInvalid = "invalid";
    private ChangePasswordToken restorationTokenValid = new ChangePasswordToken(token, userId, new Date(new Date().getTime() + 10000));
    private ChangePasswordToken restorationTokenExpired = new ChangePasswordToken(tokenExpired, userId, new Date(new Date().getTime() - 10000));

    @BeforeEach
    void setUp() {
        Mockito.when(accountRepository.findByEmail(email)).thenReturn(Optional.of(new Account(userId, email, null, null, null, null)));
        Mockito.when(accountRepository.save(Mockito.any())).thenAnswer(invocation->invocation.getArgument(0, Account.class));
        Mockito.when(tokensRepository.findById(token)).thenReturn(Optional.of(restorationTokenValid));
        Mockito.when(tokensRepository.findById(tokenExpired)).thenReturn(Optional.of(restorationTokenExpired));
        Mockito.when(tokensRepository.findById(tokenInvalid)).thenReturn(Optional.empty());
        Mockito.when(tokensRepository.save(captor.capture())).thenAnswer(invocation->invocation.getArgument(0, ChangePasswordToken.class));
        Mockito.doAnswer(invocation -> null).when(accountRepository).updateCredentials(Mockito.eq(userId), credentialCaptor.capture());
    }

    @Test
    void createPasswordRestorationRequest() {
        restorationService.createPasswordRestorationRequest(email);
        Mockito.verify(mailerService, Mockito.atLeastOnce()).sendSimpleMessage(Mockito.eq(email), Mockito.any(), Mockito.any());
    }

    @Test
    void restoreCredentials() {
        restorationService.restoreCredentials(token, newPassword);
        assert credentialCaptor.getValue().equals(EncryptionUtils.createHash(newPassword));
        Mockito.verify(tokensRepository, Mockito.atLeastOnce()).delete(restorationTokenValid);
    }

    @Test
    void restoreCredentialsExpired(){
        assertThrows(InvalidTokenException.class, () ->restorationService.restoreCredentials(tokenExpired, newPassword));
    }

    @Test
    void restoreCredentialsInvalidToken(){
        assertThrows(InvalidTokenException.class, () ->restorationService.restoreCredentials(tokenInvalid, newPassword));
    }
}