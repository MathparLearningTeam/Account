package mathpar.web.learning.account.utils.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mathpar.web.learning.account.entities.AuthenticationToken;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Date expirationDate;

    public TokenResponse(AuthenticationToken token){
        this.token = token.getToken();
        this.expirationDate = token.getExpirationDate();
    }
}
