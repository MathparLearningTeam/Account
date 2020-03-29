package mathpar.web.learning.account.services;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.utils.EncryptionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final RestorationService restorationService;

    public AccountService(AccountRepository accountRepository, RestorationService restorationService) {
        this.accountRepository = accountRepository;
        this.restorationService = restorationService;
    }

    public Optional<Account> getAccount(String email){
        return accountRepository.findByEmail(EncryptionUtils.createHash(email));
    }

    public boolean isEmailAvailable(String rawEmail){
        var principal = EncryptionUtils.createHash(rawEmail);
        return accountRepository.isEmailAvailable(principal).isEmpty();
    }

    public Account createAccount(String email, String password, String fullName){
        return accountRepository.save(new Account(email, password, fullName));
    }

    public void createTemporaryAccount(String email){
        var account = accountRepository.save(new Account(email));
        restorationService.createPasswordRestorationRequest(email);
    }

    @Transactional
    public void removeAccounts(List<Long> userIds){
        accountRepository.deleteAllByIdIn(userIds);
    }

}
