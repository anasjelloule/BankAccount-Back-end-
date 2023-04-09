package ma.voltify.bankweb.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exceptions.BankAccountNotFoundException;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.services.BankAccountserviceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/accounts")
public class RestControllerBank {
    private BankRepository bankrepository;
    private BankAccountserviceImpl bankaccountservice;

    @GetMapping({ "/", "" })
    public List<BankAccount> getaccounts() {
        return bankrepository.findAll();
    }

    // @PostMapping({ "/", "" })
    // public BankAccount saveaccount(@RequestBody ) {

    // return null;
    // }

    @GetMapping({ "/{id}", "/{id}/" })
    public BankAccount getaccountsid(@PathVariable(name = "id") String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccount(id);
    }
}
