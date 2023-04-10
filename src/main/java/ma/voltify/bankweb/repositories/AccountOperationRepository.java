package ma.voltify.bankweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.voltify.bankweb.entities.AccountOperation;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
    @Query(nativeQuery = true, value = "SELECT a.* FROM `account_operation` a INNER JOIN `bank_account` b ON b.id = a.bank_account_id INNER JOIN `customer` c ON c.id=b.customer_id where c.id=:id")
    List<AccountOperation> findByCustomerid(@Param("id") long id);
}
