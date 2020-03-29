package mathpar.web.learning.account.utils.dto.payloads;

import lombok.Data;

@Data
public class UpdateTokenPayload{
    private String token;
    private String newRole;
}