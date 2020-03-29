package mathpar.web.learning.account.utils.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IsEmailAvailableResponse {
    private boolean available;
}
