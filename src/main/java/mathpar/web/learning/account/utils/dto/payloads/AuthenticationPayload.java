package mathpar.web.learning.account.utils.dto.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationPayload{
    private String email;
    private String password;
}