package ma.voltify.bankweb.web.Graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import dtos.BankDto;
import dtos.CustomerDto;
import exceptions.BankAccountNotFoundException;
import exceptions.CustomerNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@Controller
@AllArgsConstructor
public class CustomerGraphql {
    private BankAccountserviceImpl bankaccountservice;

    @QueryMapping
    public List<CustomerDto> getCustomers() {
        System.out.println("INside graphql");
        return bankaccountservice.getCustomerList();
    }

    @QueryMapping
    public List<BankDto> getaccounts() {
        return bankaccountservice.getBankAccountlist();
    }

    @QueryMapping
    public BankDto getBankAccountDto(@Argument String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccountDto(id);
    }

    @MutationMapping
    public CustomerDto addCustomer(@Argument CustomerDto customerdto) {
        // System.out.println(customerdto);
        // return customerdto;
        return bankaccountservice.saveCustomer(customerdto);
    }

    @MutationMapping
    public int removeCustomer(@Argument Long id) throws CustomerNotFoundException {

        // return customerdto;
        System.out.println(id);
        return 9;
        // bankaccountservice.deletCustomer(id);
    }

}
