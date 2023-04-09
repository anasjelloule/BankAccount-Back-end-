package ma.voltify.bankweb.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import dtos.CustomerDto;
import ma.voltify.bankweb.entities.Customer;

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
}
