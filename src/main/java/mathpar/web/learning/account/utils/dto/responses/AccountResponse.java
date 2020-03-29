package mathpar.web.learning.account.utils.dto.responses;

import lombok.Data;
import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.entities.AuthenticationToken;

import java.util.Date;

@Data
public class AccountResponse {
    private long accountId;
    private String fullName;
    private Date creationDate;

    private TokenResponse authentication;

    public AccountResponse(Account account, AuthenticationToken token){
        this.accountId = account.getId();
        this.fullName = account.getFullName();
        this.creationDate = account.getCreationDate();

        this.authentication = new TokenResponse(token.getToken(), token.getExpirationDate());
    }
}
