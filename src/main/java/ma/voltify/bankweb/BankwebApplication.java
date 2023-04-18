package ma.voltify.bankweb;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import dtos.CustomerDto;
import enums.AccountStatus;
import enums.OperationsType;
import ma.voltify.bankweb.entities.AccountOperation;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.entities.SavingAccount;
import ma.voltify.bankweb.mappers.BankAccountmapper;
import ma.voltify.bankweb.repositories.AccountOperationRepository;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.repositories.CustomerRepository;
import ma.voltify.bankweb.services.AccountUserService;
import ma.voltify.bankweb.services.BankAccountserviceImpl;
import ma.voltify.bankweb.web.soap.Bankservicesoap;

@SpringBootApplication

// @ComponentScan(basePackages = { "mappers" })
// @EnableSwagger2
// @EnableWebMvc
// @OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0",
// description = "Employees Information"))
@Transactional
public class BankwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankwebApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner3(Bankservicesoap bankservicesoap) {
		return args -> {
			// Endpoint.publish("http://0.0.0.0:8088/", bankservicesoap);
			// bankservicesoap.getListBankDto();
			// System.out.println("Web service running on port 8088");
		};
	}

	// @Bean
	CommandLineRunner commandLineRunner2(AccountUserService userimpl) {
		return args -> {

			// fill Roles in the database
			// userimpl.addRole(AppRole.builder().rolename("USER").build());
			// userimpl.addRole(AppRole.builder().rolename("ADMIN").build());

			// userimpl.addRole(AppRole.builder().rolename("PRODUCT_MANAGER").build());
			// userimpl.addRole(AppRole.builder().rolename("CUSTOMER_MANAGER").build());
			// userimpl.addRole(AppRole.builder().rolename("BILL_MANAGER").build());
			// userimpl.addRole(AppRole.builder().rolename("CUSTOMER").build());

			// fill Users in the database

			// AppUser appuser = AppUser.builder()
			// .username("ANAS").password("1234").build();
			// userimpl.addNewUser(appuser);
			// userimpl.addNewUser(AppUser.builder()
			// .username("HAMZA")
			// .password("1234")
			// // .appRoles()
			// .build());
			// userimpl.addNewUser(AppUser.builder()
			// .username("ALI")
			// .password("1234")
			// // .appRoles()
			// .build());
			// userimpl.addNewUser(AppUser.builder()
			// .username("FATI")
			// .password("1234")
			// // .approles()
			// .build());

			// Add roles to users
			// userimpl.listusers().forEach(user -> {
			// try {
			// // userimpl.addRoleToUser(user.getUsername(), "USER");
			// } catch (UserNotFoundException | RoleNotFoundException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// });
			userimpl.addRoleToUser("ANAS", "ADMIN");
			userimpl.addRoleToUser("ALI", "PRODUCT_MANAGER");
			userimpl.addRoleToUser("HAMZA", "CUSTOMER_MANAGER");
		};
	}

	// @Bean
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
			Stream.of("Anas", "Ali", "Hamza", "Khalid", "Fati", "Ayoub", "Wissal").forEach(name -> {
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
					AccountOperation accountOperation = AccountOperation.builder()
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
