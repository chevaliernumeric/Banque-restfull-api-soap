package com.example.bank_api.wsdl;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TransferFundsRequest")
public class TransferFundsRequest {

    private Long fromAccountId;
    private Long toAccountId;
    private Double amount;
    private String currency;
    private Long creditor;
    private Long debtor;

    @XmlElement(required = true)
    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    @XmlElement(required = true)
    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }

    @XmlElement(required = true)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @XmlElement(required = true)
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @XmlElement(required = true)
    public Long getCreditor() {
        return creditor;
    }

    public void setCreditor(Long creditor) {
        this.creditor = creditor;
    }

    @XmlElement(required = true)
    public Long getDebtor() {
        return debtor;
    }

    public void setDebtor(Long debtor) {
        this.debtor = debtor;
    }
}
