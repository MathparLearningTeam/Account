package mathpar.web.learning.account.utils.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    private String token;
    private Date expirationDate;
}
