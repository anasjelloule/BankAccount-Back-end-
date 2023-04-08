package ma.voltify.bankweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.voltify.bankweb.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
