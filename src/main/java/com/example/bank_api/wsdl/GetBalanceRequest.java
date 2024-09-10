package com.example.bank_api.wsdl;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "GetBalanceRequest")
public class GetBalanceRequest {

    private Long accountId;

    @XmlElement(required = true)
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
