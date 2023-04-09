package ma.voltify.bankweb.services;

import java.util.List;

import dtos.CustomerDto;
import exceptions.BankAccountNotFoundException;

import exceptions.CustomerNotFoundException;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.Customer;

public interface BankAccountservice {
        Customer saveCustomer(Customer customer);

        List<CustomerDto> getCustomerList();

        public BankAccount saveCurrentBankAccount(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        public BankAccount saveSavingBankAccount(double Initialebalance, double overDraft, long customerId)
                        throws CustomerNotFoundException;

        BankAccount getBankAccount(String id) throws BankAccountNotFoundException;

        void debit(String id, double amount, String description);

        void credit(String id, double amount, String description);

        void transfer(String accountIdSrouce, String AccountIdDestinataire, double amount, String description);

}
