package mathpar.web.learning.account.repositories;

import mathpar.web.learning.account.entities.Account;
import mathpar.web.learning.account.utils.dto.AccountPublicInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Optional<Account> findByEmailAndPassword(String email, String password);

    @Query(value = "select 1 from accounts where email=?1", nativeQuery = true)
    Optional<Byte> isEmailAvailable(String email);

    @Modifying
    @Query(value="update accounts set password=?2 where id=?1", nativeQuery = true)
    void updateCredentials(long accountId, String newCredential);

    @Modifying
    void deleteAllByIdIn(List<Long> userId);

    @Query(value="select email, name, id from accounts where id in ?1", nativeQuery=true)
    List<AccountPublicInfo> getAccountPublicInfoForAccounts(List<Long> accountIds);
}
