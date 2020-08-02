package mathpar.web.learning.account.controllers;

import mathpar.web.learning.account.services.AccountService;
import mathpar.web.learning.account.utils.dto.payloads.CreateTemporaryCredentialsPayload;
import mathpar.web.learning.account.utils.dto.responses.AccountResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mathpar.web.learning.account.utils.AuthenticationUrls.*;

@RestController
public class PrivateAccountController {
    private final AccountService accountService;

    public PrivateAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(GET_ACCOUNT_ID)
    public long getAccountId(@RequestParam("email") String email){
        return accountService.getAccount(email).orElseThrow().getId();
    }

    @GetMapping("/account")
    public AccountResponse getAccountById(@RequestParam("id") long id){
        return new AccountResponse(accountService.getAccount(id).orElseThrow());
    }

    @PostMapping(CREATE_TEMPORARY_ACCOUNT_URL)
    public Long createTemporaryCredentials(@RequestBody CreateTemporaryCredentialsPayload payload){
        return accountService.createTemporaryAccount(payload.getEmail()).getId();
    }

    @DeleteMapping(REMOVE_ACCOUNTS)
    public void removeAccounts(@RequestParam("userIds") List<Long> userIds){
        accountService.removeAccounts(userIds);
    }
}
