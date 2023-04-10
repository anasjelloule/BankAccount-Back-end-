package ma.voltify.bankweb.services;

import java.util.List;

import dtos.CustomerDto;
import exceptions.BankAccountNotFoundException;

import exceptions.CustomerNotFoundException;
import ma.voltify.bankweb.entities.BankAccount;

public interface BankAccountservice {
        CustomerDto saveCustomer(CustomerDto customer);

        List<CustomerDto> getCustomerList();

        public BankAccount saveCurrentBankAccount(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        public BankAccount saveSavingBankAccount(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        BankAccount getBankAccount(String id) throws BankAccountNotFoundException;

        void debit(String id, double amount, String description);

        void credit(String id, double amount, String description);

        void transfer(String accountIdSrouce, String AccountIdDestinataire, double amount, String description);

        CustomerDto getCustomer(long id) throws CustomerNotFoundException;

        List<BankAccount> getcustomerBankAccounts(long id) throws CustomerNotFoundException;

        // CustomerDto updatecustomer(long id, CustomerDto customerdto) throws
        // CustomerNotFoundException;

        CustomerDto updatecustomer(CustomerDto customerdto) throws CustomerNotFoundException;

        void deletCustomer(long id) throws CustomerNotFoundException;

}
