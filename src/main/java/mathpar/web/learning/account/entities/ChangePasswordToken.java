package mathpar.web.learning.account.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.web.learning.account.utils.EncryptionUtils;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "change_password_tokens")
public class ChangePasswordToken {
    @Id
    @Column(name = "token")
    private String token;
    @Column(name = "account_id")
    private long accountId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_date")
    private Date expirationDate;

    public ChangePasswordToken(long accountId, Date expirationDate) {
        this.token = EncryptionUtils.generateString(50);
        this.accountId = accountId;
        this.expirationDate = expirationDate;
    }
}
