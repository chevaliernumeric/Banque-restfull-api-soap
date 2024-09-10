package com.example.bank_api.wsdl;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "GetBalanceResponse")
public class GetBalanceResponse {

    private Double balance;

    @XmlElement(required = true)
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
