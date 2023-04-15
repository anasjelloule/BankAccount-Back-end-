package ma.voltify.bankweb.web.Restcontrollerspring;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtos.BankDto;
import dtos.CustomerDto;
import dtos.OperationDto;
import exceptions.CustomerNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@RestController
@AllArgsConstructor
@RequestMapping({ "/customers", "/customers/" })
public class RestControllerCustomers {
    private BankAccountserviceImpl bankaccountservice;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers(HttpServletResponse response) {
        System.out.println(response.getStatus());
        return ResponseEntity.status(200).body(bankaccountservice.getCustomerList());
    }

    @PostMapping
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerdto) {
        return bankaccountservice.saveCustomer(customerdto);
    }

    @DeleteMapping({ "/{id}/", "/{id}" })
    public void deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        bankaccountservice.deletCustomer(id);
    }

    @PutMapping({ "/{id}/", "/{id}" })
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerdto)
            throws CustomerNotFoundException {
        customerdto.setId(id);
        return bankaccountservice.updatecustomer(customerdto);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public CustomerDto getCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        return bankaccountservice.getCustomer(id);
    }

    @GetMapping({ "/{id}/accounts", "/{id}/accounts/" })
    public List<BankDto> getCustomeraccounts(@PathVariable Long id) throws CustomerNotFoundException {
        // System.out.println((List<BankAccount>)
        // customerrepository.findById(id).orElse(null).getBankAccounts());
        return bankaccountservice.getcustomerBankAccounts(id);
    }

    @GetMapping({ "/{id}/operations", "/{id}/operations/" })
    public List<OperationDto> getOperations(@PathVariable Long id) throws CustomerNotFoundException {
        // System.out.println((List<BankAccount>)
        // customerrepository.findById(id).orElse(null).getBankAccounts());
        return bankaccountservice.getOperations(id);
    }

}
