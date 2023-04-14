package ma.voltify.bankweb.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dtos.BankDto;
import dtos.CurrentDto;
import dtos.CustomerDto;
import dtos.OperationDto;
import dtos.SavingDto;
import enums.AccountStatus;
import enums.OperationsType;
import exceptions.BalanceNotsuffException;
import exceptions.BankAccountNotFoundException;
import exceptions.CustomerNotFoundException;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.entities.AccountOperation;
import ma.voltify.bankweb.entities.BankAccount;
import ma.voltify.bankweb.entities.CurrentAccount;
import ma.voltify.bankweb.entities.Customer;
import ma.voltify.bankweb.entities.SavingAccount;
import ma.voltify.bankweb.mappers.BankAccountmapper;
import ma.voltify.bankweb.repositories.AccountOperationRepository;
import ma.voltify.bankweb.repositories.BankRepository;
import ma.voltify.bankweb.repositories.CustomerRepository;

@Service
@AllArgsConstructor
public class BankAccountserviceImpl implements BankAccountservice {
    private BankRepository bankrepository;
    private CustomerRepository customerrepository;
    private BankAccountmapper bankmapper;
    private AccountOperationRepository accountoperationrepository;

    @Override
    public void credit(String id, double amount, String description) throws BankAccountNotFoundException {
        BankAccount bankaccount = getBankAccount(id);
        bankaccount.setBalance(bankaccount.getBalance() + amount);
        AccountOperation accoperation = AccountOperation.builder()
                .amount(amount)
                .description(description)
                .type(OperationsType.CEDIT)
                .bankAccount(bankaccount)
                .build();
        accountoperationrepository.save(accoperation);
        // bankaccount.setAccountoperations(null);
    }

    @Override
    public void debit(String id, double amount, String description)
            throws BankAccountNotFoundException, BalanceNotsuffException {
        BankAccount bankaccount = getBankAccount(id);
        if (bankaccount.getBalance() <= 0)
            throw new BalanceNotsuffException("Balance not sufficient");
        bankaccount.setBalance(bankaccount.getBalance() - amount);

        AccountOperation accoperation = AccountOperation.builder()
                .amount(amount)
                .description(description)
                .type(OperationsType.DEBIT)
                .bankAccount(bankaccount)
                .build();
        accountoperationrepository.save(accoperation);

    }

    @Override
    public void transfer(String accountIdSrouce, String AccountIdDestinataire, double amount, String description)
            throws BankAccountNotFoundException, BalanceNotsuffException {
        debit(accountIdSrouce, amount, description);
        credit(AccountIdDestinataire, amount, description);
    }

    @Override
    public BankDto getBankAccountDto(String id) throws BankAccountNotFoundException {
        BankAccount bank = bankrepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));
        BankDto bankdto = null;
        if (bank instanceof SavingAccount)
            bankdto = bankmapper.fromSaving((SavingAccount) bank);
        if (bank instanceof CurrentAccount)
            bankdto = bankmapper.fromCurrent((CurrentAccount) bank);
        return bankdto;
    }

    @Override
    public BankAccount getBankAccount(String id) throws BankAccountNotFoundException {
        BankAccount bank = bankrepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account not found"));

        return bank;
    }

    @Override
    public List<BankDto> getBankAccountlist() {
        List<BankDto> bankdtos = bankrepository.findAll().stream().map(bank -> {
            BankDto bankdto = null;
            if (bank instanceof SavingAccount)
                bankdto = bankmapper.fromSaving((SavingAccount) bank);
            if (bank instanceof CurrentAccount)
                bankdto = bankmapper.fromCurrent((CurrentAccount) bank);
            return bankdto;
        }).collect(Collectors.toList());

        return bankdtos;
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
    public List<BankDto> getcustomerBankAccounts(long id) throws CustomerNotFoundException {
        List<BankDto> bankdtos = customerrepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"))
                .getBankAccounts().stream().map(bank -> {
                    BankDto bankdto = null;
                    if (bank instanceof SavingAccount)
                        bankdto = bankmapper.fromSaving((SavingAccount) bank);
                    if (bank instanceof CurrentAccount)
                        bankdto = bankmapper.fromCurrent((CurrentAccount) bank);
                    return bankdto;
                }).collect(Collectors.toList());
        ;
        return bankdtos;
    }

    @Override
    public CurrentDto saveCurrentBankAccountDto(double Initialebalance, double overDraft, long customerId)
            throws CustomerNotFoundException {
        Customer customer = customerrepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));

        CurrentAccount bankaccount = CurrentAccount.builder().overDraft(overDraft).build();
        bankaccount.setCreatedAt(new Date());
        bankaccount.setCustomer(customer);
        bankaccount.setBalance(Initialebalance);
        bankaccount.setStatus(AccountStatus.ACTIVATED);
        bankaccount.setId(UUID.randomUUID().toString());
        return bankmapper.fromCurrent(bankrepository.save(bankaccount));
    }

    @Override
    public SavingDto saveSavingBankAccountDto(double Initialebalance, double interestrate, long customerId)
            throws CustomerNotFoundException {

        Customer customer = customerrepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " + customerId));

        SavingAccount bankaccount = SavingAccount.builder().interestrate(interestrate).build();
        bankaccount.setCreatedAt(new Date());
        bankaccount.setCustomer(customer);
        bankaccount.setBalance(Initialebalance);
        bankaccount.setStatus(AccountStatus.ACTIVATED);
        bankaccount.setId(UUID.randomUUID().toString());

        return bankmapper.fromSaving(bankrepository.save(bankaccount));
    }

    @Override
    public List<OperationDto> getOperations(long id) throws CustomerNotFoundException {

        // Customer customer = customerrepository.findById(id)
        // .orElseThrow(() -> new CustomerNotFoundException("Customer not found: " +
        // id));
        // List<BankAccount> banks = customer.getBankAccounts();
        return accountoperationrepository.findByCustomerid(id).stream().map(acc -> bankmapper.fromOperation(acc))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationDto> AccountHistory(String id) throws CustomerNotFoundException, BankAccountNotFoundException {
        return getBankAccount(id).getAccountoperations().stream()
                .map(acc -> {
                    return bankmapper.fromOperation(acc);
                }).collect(Collectors.toList());
        // return null;
    }

}
