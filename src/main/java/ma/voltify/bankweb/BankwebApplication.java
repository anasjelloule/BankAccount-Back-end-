package ma.voltify.bankweb;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.SpringVersion;
import org.springframework.transaction.annotation.Transactional;
import dtos.CustomerDto;
import enums.AccountStatus;
import enums.OperationsType;
import ma.voltify.bankweb.entities.AccountOperations;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.entities.SavingAccount;
import ma.voltify.bankweb.mappers.BankAccountmapper;
import ma.voltify.bankweb.repositories.AccountOperationRepository;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.repositories.CustomerRepository;
import ma.voltify.bankweb.services.BankAccountserviceImpl;
// import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
// @ComponentScan(basePackages = { "mappers" })
// @EnableSwagger2
// @EnableWebMvc
@Transactional
public class BankwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankwebApplication.class, args);
		System.out.println("Version" + SpringVersion.getVersion());
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerrepository, BankRepository bankrepository,
			AccountOperationRepository accountoperationrepository) {
		return args -> {
			// BankAccount bankaccount =
			// bankrepository.findById("3fadeb7a-3cda-42fa-9686-80f9a454cd1b")
			// .orElse(null);
			// System.out.println(bankaccount);
			// // System.out.println("Account Operations");
			// bankaccount.getAccountoperations().forEach(System.out::println);
			// System.out.println("/*******************************************************/");
			// BankAccount bankaccount2 =
			// bankrepository.findById("dce14ab7-6e87-46c5-935a-cb55f042098e")
			// .orElse(null);
			// System.out.println(bankaccount2);
			// bankaccount2.getAccountoperations().forEach(System.out::println);
		};
	}

	// @Bean
	CommandLineRunner start(BankAccountserviceImpl bankservice, BankRepository bankrepository,
			AccountOperationRepository accountoperationrepository, BankAccountmapper bankmapper) {
		return args -> {
			// Create Customer
			Stream.of("Anas", "Ali", "Hamza", "Khalid").forEach(name -> {
				CustomerDto customerdto = CustomerDto.builder()
						.name(name)
						.email(name + "@gmail.com")
						.build();
				bankservice.saveCustomer(customerdto);
			});
			// Create Current Account & Saving Account both for each customer
			bankservice.getCustomerList().forEach(customer -> {
				Customer cust = bankmapper.fromCustomerDto(customer);
				// CurrentAccount
				CurrentAccount currentaccount = CurrentAccount.builder().overDraft(9000).build();
				currentaccount.setCurrency("DHs");
				currentaccount.setBalance(Math.random() * 1000);
				currentaccount.setStatus(AccountStatus.ACTIVATED);
				currentaccount.setCreatedAt(new Date());
				currentaccount.setCustomer(cust);
				currentaccount.setId(UUID.randomUUID().toString());
				bankrepository.save(currentaccount);
				// Saving Account
				SavingAccount savingaccount = SavingAccount.builder().interestrate(10).build();
				savingaccount.setBalance(Math.random() * 1000);
				savingaccount.setCurrency("$");
				savingaccount.setStatus(AccountStatus.ACTIVATED);
				savingaccount.setCreatedAt(new Date());
				savingaccount.setCustomer(cust);
				savingaccount.setId(UUID.randomUUID().toString());
				bankrepository.save(savingaccount);

			});
			// Create Account Operations
			bankrepository.findAll().forEach(bank -> {
				for (int i = 0; i < 10; i++) {
					AccountOperations accountOperation = AccountOperations.builder()
							.Date(new Date())
							.amount(Math.random() * 1200)
							.type(Math.random() > 0.5 ? OperationsType.CEDIT : OperationsType.DEBIT)
							.bankAccount(bank)
							.build();

					accountoperationrepository.save(accountOperation);
				}

			});

		};
	}

}
