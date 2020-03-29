package mathpar.web.learning.account.utils.dto.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTemporaryCredentialsPayload {
    private long userId;
    private String email;
    private String institutionType;
}