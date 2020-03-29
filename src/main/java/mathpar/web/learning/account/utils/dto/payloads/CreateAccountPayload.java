package mathpar.web.learning.account.utils.dto.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountPayload {
    private String email;
    private String password;
    private String fullName;
}