package ma.voltify.bankweb.web.Restcontrollerspring;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dtos.BankDto;
import dtos.CurrentDto;
import dtos.OperationDto;
import dtos.SavingDto;
import exceptions.BankAccountNotFoundException;
import exceptions.CustomerNotFoundException;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/accounts")
public class RestControllerBank {
    // private BankRepository bankrepository;
    private BankAccountserviceImpl bankaccountservice;

    @GetMapping({ "/", "" })
    public ResponseEntity<List<BankDto>> getaccounts() {
        return ResponseEntity.status(200).body(bankaccountservice.getBankAccountlist());
    }

    @PostMapping({ "/", "" })
    public BankDto saveaccount(@RequestBody BankDto bankDto) throws CustomerNotFoundException {
        System.out.println(bankDto);
        if (bankDto instanceof SavingDto)
            bankDto = bankaccountservice.saveSavingBankAccountDto(bankDto.getBalance(),
                    ((SavingDto) bankDto).getInterestrate(),
                    bankDto.getCustomer().getId());
        if (bankDto instanceof CurrentDto)
            bankDto = bankaccountservice.saveCurrentBankAccountDto(bankDto.getBalance(),
                    ((CurrentDto) bankDto).getOverDraft(),
                    bankDto.getCustomer().getId());
        return bankDto;
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public BankDto getaccountsid(@PathVariable String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccountDto(id);
    }

    @GetMapping({ "/{id}/operations", "/{id}/operations/" })
    public List<OperationDto> getOperations(@PathVariable String id)
            throws CustomerNotFoundException, BankAccountNotFoundException {
        return bankaccountservice.AccountHistory(id);
    }
}
