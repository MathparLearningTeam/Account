package mathpar.web.learning.account.utils.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsAuthenticatedResponse {
    private boolean valid;
}
