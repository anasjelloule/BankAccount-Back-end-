package ma.voltify.bankweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.AccountOperations;

public interface AccountOperationRepository extends JpaRepository<AccountOperations, Long> {

}
