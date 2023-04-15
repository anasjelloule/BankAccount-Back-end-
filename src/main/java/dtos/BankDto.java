package dtos;

import java.util.Date;

import org.glassfish.jaxb.runtime.XmlAccessorFactory;

import enums.AccountStatus;
import jakarta.jws.WebResult;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.Data;

@Data
@XmlRootElement(name = "BankDtosoap")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankDto {
    private String id;
    @XmlTransient
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    @XmlElement(name = "ResultDto")
    // @WebResult(name = "ADDRTO")
    private CustomerDto customer;
}
