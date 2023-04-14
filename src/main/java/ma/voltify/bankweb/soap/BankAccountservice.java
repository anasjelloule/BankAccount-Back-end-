package ma.voltify.bankweb.soap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ma.voltify.bankweb.services.BankAccountserviceImpl;

@Data
// @RequiredArgsConstructor
@AllArgsConstructor
// @Webservice
public class BankAccountservice {
    private BankAccountserviceImpl bankaccountservice;

}
