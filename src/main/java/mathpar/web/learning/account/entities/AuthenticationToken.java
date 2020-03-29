package mathpar.web.learning.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.web.learning.account.utils.DateUtils;
import mathpar.web.learning.account.utils.TokenUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "authentication_tokens")
public class AuthenticationToken {
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "account_id")
    private long accountId;
    @Column(name = "expiration_date")
    private Date expirationDate;
    @Column(name = "issuer")
    private String issuer;

    public AuthenticationToken(long accountId, String issuer) {
        this.accountId = accountId;
        this.token = TokenUtils.createToken(accountId);
        this.expirationDate = DateUtils.getOffsetDate(new Date(), DateUtils.DAY);
        this.issuer = issuer;
    }
}
