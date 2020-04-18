package mathpar.web.learning.account.utils.dto.responses;

import lombok.Data;
import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.entities.AuthenticationToken;

import java.util.Date;

@Data
public class RegistrationResponse {
    private long accountId;
    private String email;
    private String fullName;
    private Date creationDate;

    private TokenResponse authentication;

    public RegistrationResponse(Account account, AuthenticationToken token){
        this.accountId = account.getId();
        this.email = account.getEmail();
        this.fullName = account.getFullName();
        this.creationDate = account.getCreationDate();

        this.authentication = new TokenResponse(token);
    }
}
