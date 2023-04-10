package ma.voltify.bankweb.mappers;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dtos.CurrentDto;
import dtos.CustomerDto;
import dtos.OperationDto;
import dtos.SavingDto;
import jakarta.persistence.EntityManager;
import ma.voltify.bankweb.entities.AccountOperation;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.entities.SavingAccount;

// Mapstruct
@Service
public class BankAccountmapper {
    public Customer fromCustomerDto(CustomerDto customerdto) {
        Customer customer = Customer.builder().build();
        BeanUtils.copyProperties(customerdto, customer);
        return customer;
    }

    public CustomerDto fromCustomer(Customer customer) {
        CustomerDto customerdto = CustomerDto.builder().build();
        BeanUtils.copyProperties(customer, customerdto);
        return customerdto;
    }

    public SavingAccount fromSavingDto(SavingDto savingdto) {
        SavingAccount saving = SavingAccount.builder().build();
        BeanUtils.copyProperties(savingdto, saving);
        return saving;
    }

    public SavingDto fromSaving(SavingAccount saving) {
        SavingDto savingDto = SavingDto.builder().build();
        BeanUtils.copyProperties(saving, savingDto);
        if (saving.getCustomer() != null)
            savingDto.setCustomer(fromCustomer(saving.getCustomer()));
        return savingDto;
    }

    public CurrentAccount fromCurrentDto(CurrentDto currentDto) {
        CurrentAccount current = CurrentAccount.builder().build();
        BeanUtils.copyProperties(currentDto, current);
        return current;
    }

    public CurrentDto fromCurrent(CurrentAccount current) {
        CurrentDto currentDto = CurrentDto.builder().build();
        BeanUtils.copyProperties(current, currentDto);
        if (current.getCustomer() != null)
            currentDto.setCustomer(fromCustomer(current.getCustomer()));
        return currentDto;
    }

    public AccountOperation fromOperationDto(CurrentDto operationdto) {
        AccountOperation operation = AccountOperation.builder().build();
        BeanUtils.copyProperties(operationdto, operation);
        return operation;
    }

    public OperationDto fromOperation(AccountOperation operation) {
        OperationDto operationdto = OperationDto.builder().build();
        BeanUtils.copyProperties(operation, operationdto);
        BankAccount bankacc = (BankAccount) Hibernate.unproxy(operation.getBankAccount());
        if (bankacc != null) {
            if (bankacc instanceof CurrentAccount) {
                operationdto.setBankDto(fromCurrent((CurrentAccount) bankacc));

            }
            if (bankacc instanceof SavingAccount) {
                operationdto.setBankDto(fromSaving((SavingAccount) bankacc));
            }
        }
        operationdto.setBankId(bankacc.getId());
        return operationdto;
    }
}
