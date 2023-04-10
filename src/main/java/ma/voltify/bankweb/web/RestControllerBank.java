package ma.voltify.bankweb.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtos.BankDto;
import dtos.SavingDto;
import exceptions.BankAccountNotFoundException;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/accounts")
public class RestControllerBank {
    // private BankRepository bankrepository;
    private BankAccountserviceImpl bankaccountservice;

    @GetMapping({ "/", "" })
    public List<BankDto> getaccounts() {
        return bankaccountservice.getBankAccountlist();
    }

    // @PostMapping({ "/", "" })
    // public BankDto saveaccount(@RequestBody BankDto bankDto) {
    // if(bankDto instanceof SavingDto)
    // bankaccountservice.saveSavingBankAccountDto(0, 0, 0)
    // return null;
    // }

    @GetMapping({ "/{id}", "/{id}/" })
    public BankDto getaccountsid(@PathVariable(name = "id") String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccount(id);
    }
}
