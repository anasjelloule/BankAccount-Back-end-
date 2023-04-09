package ma.voltify.bankweb.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtos.CustomerDto;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.repositories.CustomerRepository;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/customers")
public class RestControllerCustomers {
    private CustomerRepository customerrepository;
    private BankAccountserviceImpl bankaccountservice;

    @GetMapping({ "/", "" })
    public List<CustomerDto> getCustomers() {
        return bankaccountservice.getCustomerList();
    }

    // @PostMapping({ "/", "" })
    // public Customer saveCustomer(@RequestBody) {
    // return customerrepository.findAll();
    // }

    @GetMapping({ "/{id}", "/{id}/" })

    public Customer getCustomer(@PathVariable(name = "id") Long id) {
        return customerrepository.findById(id).orElse(null);
    }

    @GetMapping({ "/{id}/accounts", "/{id}/accounts/" })
    public List<BankAccount> getCustomeraccounts(@PathVariable(name = "id") Long id) {
        System.out.println((List<BankAccount>) customerrepository.findById(id).orElse(null).getBankAccounts());
        return (List<BankAccount>) customerrepository.findById(id).orElse(null).getBankAccounts();
    }

}
