package mathpar.web.learning.account.services;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.repositories.AccountRepository;
import mathpar.web.learning.account.utils.dto.AccountPublicInfo;
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
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> getAccount(long id){
        return accountRepository.findById(id);
    }

    public boolean isEmailAvailable(String email){
        return accountRepository.isEmailAvailable(email).isEmpty();
    }

    public Account createAccount(String email, String password, String fullName){
        return accountRepository.save(new Account(email, password, fullName));
    }

    public Account createTemporaryAccount(String email){
        var account = accountRepository.save(new Account(email));
        restorationService.createPasswordRestorationRequest(email);
        return account;
    }

    @Transactional
    public void removeAccounts(List<Long> userIds){
        accountRepository.deleteAllByIdIn(userIds);
    }

    public List<AccountPublicInfo> getPublicInfo(List<Long> accountIds){
        return accountRepository.getAccountPublicInfoForAccounts(accountIds);
    }
}
