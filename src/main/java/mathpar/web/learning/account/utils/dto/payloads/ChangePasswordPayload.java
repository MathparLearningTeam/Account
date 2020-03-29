package mathpar.web.learning.account.utils.dto.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordPayload {
    private String token;
    private String newPassword;
}