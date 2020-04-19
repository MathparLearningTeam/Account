package mathpar.web.learning.account.services;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.entities.ChangePasswordToken;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.repositories.ChangePasswordTokenRepository;
import mathpar.web.learning.account.services.mailing.MailerService;
import mathpar.web.learning.account.utils.DateUtils;
import mathpar.web.learning.account.utils.EncryptionUtils;
import mathpar.web.learning.account.utils.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class RestorationService {
    private final AccountRepository accountRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final MailerService mailerService;

    public RestorationService(AccountRepository accountRepository, ChangePasswordTokenRepository changePasswordTokenRepository, MailerService mailerService) {
        this.accountRepository = accountRepository;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
        this.mailerService = mailerService;
    }

    public void createPasswordRestorationRequest(String email){
        var account = accountRepository.findByEmail(email);
        if (account.isEmpty()) return;
        createPasswordRestorationRequest(account.get(), email);
    }

    public void createPasswordRestorationRequest(Account account, String email){
        var credentials = changePasswordTokenRepository.save(new ChangePasswordToken(account.getId(), DateUtils.getOffsetDate(new Date(), DateUtils.DAY)));
        mailerService.sendSimpleMessage(email, "Mathpar reset password request", "The reset password request was created for your user in Mathpar system. Follow the link to proceed:\nhttp://mathpar.ukma.edu.ua/learning/#/restore/"+credentials.getToken()+"\n\nThis link will expire at "+credentials.getExpirationDate());
    }

    @Transactional
    public void restoreCredentials(String token, String newPassword){
        var temporaryToken = changePasswordTokenRepository.findById(token).orElseThrow(InvalidTokenException::new);
        if(temporaryToken.getExpirationDate().before(new Date())) throw new InvalidTokenException("Token has been expired");
        accountRepository.updateCredentials(temporaryToken.getAccountId(), EncryptionUtils.createHash(newPassword));
        changePasswordTokenRepository.delete(temporaryToken);
    }
}
