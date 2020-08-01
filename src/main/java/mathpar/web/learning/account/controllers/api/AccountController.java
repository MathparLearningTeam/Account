package mathpar.web.learning.account.controllers.api;

import io.swagger.annotations.Api;
import mathpar.web.learning.account.services.AccountService;
import mathpar.web.learning.account.services.AuthenticationService;
import mathpar.web.learning.account.utils.PublicApi;
import mathpar.web.learning.account.utils.SecurityUtils;
import mathpar.web.learning.account.utils.dto.AccountPublicInfo;
import mathpar.web.learning.account.utils.dto.payloads.CreateAccountPayload;
import mathpar.web.learning.account.utils.dto.responses.AccountResponse;
import mathpar.web.learning.account.utils.dto.responses.IsEmailAvailableResponse;
import mathpar.web.learning.account.utils.dto.responses.RegistrationResponse;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(ACCOUNT_URL)
    @PreAuthorize("hasRole('ROLE_ACCOUNT')")
    public AccountResponse getAccount(){
        long id = SecurityUtils.getCurrentAuthentication().getPrincipal();
        return new AccountResponse(accountService.getAccount(id).orElseThrow());
    }

    @PostMapping(ACCOUNT_URL)
    public RegistrationResponse createAccount(@RequestBody CreateAccountPayload payload, @RequestHeader("user-agent") String userAgent){
        var account =accountService.createAccount(payload.getEmail(), payload.getPassword(), payload.getFullName());
        var token = authenticationService.authenticate(payload.getEmail(), payload.getPassword(), userAgent);
        return new RegistrationResponse(account, token);
    }

    @DeleteMapping(ACCOUNT_URL)
    public void deleteAccount(@RequestParam("accountId") long accountId){
        accountService.removeAccounts(List.of(accountId));
    }

    @GetMapping("/getPublicInfo")
    public List<AccountPublicInfo> getPublicInfo(@RequestParam(value = "accountIds") List<Long> accountIds){
        if(accountIds.isEmpty()) return List.of();
        return accountService.getPublicInfo(accountIds);
    }
}
