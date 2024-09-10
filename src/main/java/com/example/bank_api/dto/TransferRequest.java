package com.example.bank_api.dto;



public class TransferRequest {

    private Long creditor;
    private Long debtor;
    private Double amount;
    private String currency;

    public Long getCreditor() {
        return creditor;
    }

    public void setCreditor(Long creditor) {
        this.creditor = creditor;
    }

    public Long getDebtor() {
        return debtor;
    }

    public void setDebtor(Long debtor) {
        this.debtor = debtor;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


