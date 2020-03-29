package mathpar.web.learning.account.repositories;

import mathpar.web.learning.account.entities.ChangePasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangePasswordTokenRepository extends JpaRepository<ChangePasswordToken, String> {
    void deleteAllByAccountIdIn(List<Long> userId);
}
