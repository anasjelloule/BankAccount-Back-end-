package ma.voltify.bankweb.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dtos.CustomerDto;
import enums.AccountStatus;
import exceptions.BankAccountNotFoundException;
import exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.entities.SavingAccount;
import ma.voltify.bankweb.mappers.BankAccountmapper;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.repositories.CustomerRepository;

@Service
@AllArgsConstructor
public class BankAccountserviceImpl implements BankAccountservice {
    private BankRepository bankrepository;
    private CustomerRepository customerrepository;
    private BankAccountmapper bankmapper;

    @Override
    public void credit(String id, double amount, String description) {

    }

    @Override
    public void debit(String id, double amount, String description) {

    }

    @Override
    public BankAccount getBankAccount(String id) throws BankAccountNotFoundException {
        return bankrepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
    }

    @Override
    public List<CustomerDto> getCustomerList() {
        List<CustomerDto> result = customerrepository.findAll().stream().map(cus -> bankmapper.fromCustomer(cus))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public CurrentAccount saveCurrentBankAccount(double Initialebalance, double overDraft, long customerId)
            throws CustomerNotFoundException {

        Customer customer = customerrepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));

        CurrentAccount bankaccount = CurrentAccount.builder().overDraft(overDraft).build();
        bankaccount.setCreatedAt(new Date());
        bankaccount.setCustomer(customer);
        bankaccount.setBalance(Initialebalance);
        bankaccount.setStatus(AccountStatus.ACTIVATED);
        bankaccount.setId(UUID.randomUUID().toString());

        return bankrepository.save(bankaccount);
    }

    @Override
    public SavingAccount saveSavingBankAccount(double Initialebalance, double interestrate, long customerId)
            throws CustomerNotFoundException {

        Customer customer = customerrepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));

        SavingAccount bankaccount = SavingAccount.builder().interestrate(interestrate).build();
        bankaccount.setCreatedAt(new Date());
        bankaccount.setCustomer(customer);
        bankaccount.setBalance(Initialebalance);
        bankaccount.setStatus(AccountStatus.ACTIVATED);
        bankaccount.setId(UUID.randomUUID().toString());

        return bankrepository.save(bankaccount);
    }

    @Override
    public void transfer(String accountIdSrouce, String AccountIdDestinataire, double amount, String description) {

    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerdto) {
        Customer customer = customerrepository.save(bankmapper.fromCustomerDto(customerdto));
        return bankmapper.fromCustomer(customer);
    }

    @Override
    public CustomerDto getCustomer(long id) throws CustomerNotFoundException {
        Customer customer = customerrepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return bankmapper.fromCustomer(customer);
    }

    @Override
    public void deletCustomer(long id) throws CustomerNotFoundException {
        customerrepository.deleteById(id);
    }

    @Override
    public CustomerDto updatecustomer(CustomerDto customerdto) throws CustomerNotFoundException {

        // Customer customer = customerrepository.findById(id)
        // .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        // BeanUtils.copyProperties(customerdto, customer);
        // customer.setId(id);
        Customer customer = bankmapper.fromCustomerDto(customerdto);

        customer = customerrepository.save(customer);

        return bankmapper.fromCustomer(customer);
    }

    @Override
    public List<BankAccount> getcustomerBankAccounts(long id) throws CustomerNotFoundException {
        return null;
    }

}
