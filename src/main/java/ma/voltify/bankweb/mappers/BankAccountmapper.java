package ma.voltify.bankweb.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dtos.CurrentDto;
import dtos.CustomerDto;
import dtos.SavingDto;
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
        return currentDto;
    }
}
