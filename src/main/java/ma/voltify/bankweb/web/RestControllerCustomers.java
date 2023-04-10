package ma.voltify.bankweb.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtos.CustomerDto;
import exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.BankAccount;
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

    @PostMapping({ "/", "" })
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerdto) {
        return bankaccountservice.saveCustomer(customerdto);
    }

    @DeleteMapping({ "/{id}/", "/{id}" })
    public void deleteCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        bankaccountservice.deletCustomer(id);
    }

    @PutMapping({ "/{id}/", "/{id}" })
    public CustomerDto updateCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerdto)
            throws CustomerNotFoundException {
        customerdto.setId(id);
        return bankaccountservice.updatecustomer(customerdto);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public CustomerDto getCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        return bankaccountservice.getCustomer(id);
    }

    @GetMapping({ "/{id}/accounts", "/{id}/accounts/" })
    public List<BankAccount> getCustomeraccounts(@PathVariable(name = "id") Long id) {
        System.out.println((List<BankAccount>) customerrepository.findById(id).orElse(null).getBankAccounts());
        return (List<BankAccount>) customerrepository.findById(id).orElse(null).getBankAccounts();
    }

}
