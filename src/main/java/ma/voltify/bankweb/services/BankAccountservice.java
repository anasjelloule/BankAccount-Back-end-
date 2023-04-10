package ma.voltify.bankweb.services;

import java.util.List;

import dtos.BankDto;
import dtos.CurrentDto;
import dtos.CustomerDto;
import dtos.SavingDto;
import exceptions.BankAccountNotFoundException;

import exceptions.CustomerNotFoundException;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.SavingAccount;

public interface BankAccountservice {
        CustomerDto saveCustomer(CustomerDto customer);

        List<CustomerDto> getCustomerList();

        public CurrentAccount saveCurrentBankAccount(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        public SavingAccount saveSavingBankAccount(double Initialebalance, double interestrate, long customerId)
                        throws CustomerNotFoundException;

        BankDto getBankAccount(String id) throws BankAccountNotFoundException;

        List<BankDto> getBankAccountlist();

        // List<BankDto> getBankAccountlistnocustomer();

        void debit(String id, double amount, String description);

        void credit(String id, double amount, String description);

        void transfer(String accountIdSrouce, String AccountIdDestinataire, double amount, String description);

        CustomerDto getCustomer(long id) throws CustomerNotFoundException;

        List<BankAccount> getcustomerBankAccounts(long id) throws CustomerNotFoundException;

        // CustomerDto updatecustomer(long id, CustomerDto customerdto) throws
        // CustomerNotFoundException;

        CustomerDto updatecustomer(CustomerDto customerdto) throws CustomerNotFoundException;

        void deletCustomer(long id) throws CustomerNotFoundException;

        public CurrentDto saveCurrentBankAccountDto(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        public SavingDto saveSavingBankAccountDto(double Initialebalance, double interestrate, long customerId)
                        throws CustomerNotFoundException;

}
