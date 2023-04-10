package ma.voltify.bankweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.Customer;

public interface BankRepository extends JpaRepository<BankAccount, String> {

    List<BankAccount> findByCustomer(Customer customer);
}
