package ma.voltify.bankweb.web.RestFuljaxws;

import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Component;

import dtos.BankDto;
import dtos.CustomerDto;
import exceptions.BankAccountNotFoundException;
import jakarta.ws.rs.POST;
import jakarta.inject.Inject;
import jakarta.jws.soap.InitParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@Component
@Path("/api")
@AllArgsConstructor
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class BankserviceRestJaxRSApi {

    private BankAccountserviceImpl bankaccountservice;

    @GET
    @Path("/val/{mn}")
    public Double getval(@PathParam(value = "mn") Double mn) {
        return mn * 90;
    }

    @GET
    @Path("/accounts/{id}")
    public BankDto getaccount(@PathParam(value = "id") String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccountDto(id);
    }

    @GET
    @Path("/accounts")
    public List<BankDto> getaccounts() throws BankAccountNotFoundException {
        System.out.println("Called GET");
        return bankaccountservice.getBankAccountlist();
    }

    @POST
    @Path("/save")
    public CustomerDto savCustomerDto(CustomerDto customer) {
        System.out.println("Called");
        return bankaccountservice.saveCustomer(customer);
    }

    @DELETE
    @Path("/accounts")
    public double get() {
        System.out.println("Called");
        return 90;
    }
}
