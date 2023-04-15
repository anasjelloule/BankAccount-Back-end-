package ma.voltify.bankweb.web.soap;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import dtos.BankDto;
import exceptions.BankAccountNotFoundException;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.AllArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

// @Data
// @RequiredArgsConstructor
@Service
@AllArgsConstructor
// @NoArgsConstructor
@WebService(serviceName = "BankWS")
public class Bankservicesoap {
    private BankAccountserviceImpl bankaccountservice;

    @WebMethod(operationName = "getBankDto")
    public BankDto getBankDto(@WebParam(name = "ID") String id) throws BankAccountNotFoundException {
        return bankaccountservice.getBankAccountDto(id);
        // BankDto bankdto = new BankDto();
        // bankdto.setBalance(90000);
        // bankdto.setCurrency("MAD");
        // bankdto.setStatus(AccountStatus.ACTIVATED);
        // return bankdto;
    }

    @WebMethod(operationName = "getListBankDto")
    public List<BankDto> getListBankDto() {
        // bankaccountservice.getBankAccountlist().forEach(acc ->
        // System.out.println(acc.getCustomer()));
        return bankaccountservice.getBankAccountlist();
        // BankDto bankdto = new BankDto();
        // bankdto.setBalance(90000);
        // bankdto.setCurrency("MAD");
        // bankdto.setStatus(AccountStatus.ACTIVATED);
        // return bankdto;
    }

    @WebMethod
    public Double getval(Double mn) {
        return mn * 90;
    }
}
