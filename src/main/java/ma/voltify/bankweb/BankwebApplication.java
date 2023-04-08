package ma.voltify.bankweb;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.repositories.AccountOperationRepository;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.repositories.CustomerRepository;

@SpringBootApplication
public class BankwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankwebApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerrepository, BankRepository bankrepository,
			AccountOperationRepository accountOperationrepository) {
		return args -> {
			Stream.of("Anas", "Ali", "Hamza", "Khalid").forEach(name -> {
				Customer customer = Customer.builder()
						.name(name)
						.email(name + "@gmail.com")
						.build();
				customerrepository.save(customer);
			});
		};
	}
}
