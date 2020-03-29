package mathpar.web.learning.account.controllers.api;

import io.swagger.annotations.Api;
import mathpar.web.learning.account.services.AccountService;
import mathpar.web.learning.account.services.AuthenticationService;
import mathpar.web.learning.account.utils.PublicApi;
import mathpar.web.learning.account.utils.dto.payloads.CreateAccountPayload;
import mathpar.web.learning.account.utils.dto.responses.AccountResponse;
import mathpar.web.learning.account.utils.dto.responses.IsEmailAvailableResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static mathpar.web.learning.account.utils.AuthenticationUrls.ACCOUNT_URL;
import static mathpar.web.learning.account.utils.AuthenticationUrls.IS_EMAIL_AVAILABLE;

@RestController
@PublicApi
@Api(tags = "Account")
public class AccountController {
    private final AccountService accountService;
    private final AuthenticationService authenticationService;

    public AccountController(AccountService accountService, AuthenticationService authenticationService) {
        this.accountService = accountService;
        this.authenticationService = authenticationService;
    }

    @GetMapping(IS_EMAIL_AVAILABLE)
    public IsEmailAvailableResponse isPrincipalAvailable(@RequestParam("email") String email){
        return new IsEmailAvailableResponse(accountService.isEmailAvailable(email));
    }

    @PostMapping(ACCOUNT_URL)
    public AccountResponse createCredentials(@RequestBody CreateAccountPayload payload, @RequestHeader("user-agent") String userAgent){
        var account =accountService.createAccount(payload.getEmail(), payload.getPassword(), payload.getFullName());
        var token = authenticationService.authenticate(payload.getEmail(), payload.getPassword(), userAgent);
        return new AccountResponse(account, token);
    }

    @DeleteMapping(ACCOUNT_URL)
    public void deleteAccount(@RequestParam("accountId") long accountId){
        accountService.removeAccounts(List.of(accountId));
    }
}
