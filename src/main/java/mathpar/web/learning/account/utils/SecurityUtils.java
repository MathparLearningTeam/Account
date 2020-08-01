package mathpar.web.learning.account.utils;

import mathpar.web.learning.account._configs.security.UserAuthentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserAuthentication getCurrentAuthentication(){
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
