package mathpar.web.learning.account.repositories;

import mathpar.web.learning.account.entities.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, String> {
    void deleteAllByAccountIdIn(List<Long> userIds);
}
