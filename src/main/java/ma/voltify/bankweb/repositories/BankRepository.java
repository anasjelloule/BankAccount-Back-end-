package ma.voltify.bankweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.BankAccount;

public interface BankRepository extends JpaRepository<BankAccount, String> {

}
