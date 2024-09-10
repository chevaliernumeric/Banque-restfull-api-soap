package com.example.bank_api.service;

import com.example.bank_api.wsdl.GetBalanceRequest;
import com.example.bank_api.wsdl.GetBalanceResponse;
import com.example.bank_api.wsdl.GetTransactionsRequest;
import com.example.bank_api.wsdl.GetTransactionsResponse;
import com.example.bank_api.wsdl.TransferFundsRequest;
import com.example.bank_api.wsdl.TransferFundsResponse;
import com.example.bank_api.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BankServiceEndpoint {

    private static final String NAMESPACE_URI = "http://com.example.bank_api";

    @Autowired
    private AccountService accountService;

    // Get balance method
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBalanceRequest")
    @ResponsePayload
    public GetBalanceResponse getBalance(@RequestPayload GetBalanceRequest request) {
        GetBalanceResponse response = new GetBalanceResponse();
        response.setBalance(accountService.getAccountBalance(request.getAccountId()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransactionsRequest")
    @ResponsePayload
    public GetTransactionsResponse getTransactions(@RequestPayload GetTransactionsRequest request) {

        Page<Transaction> transactionsPage = accountService.getTransactionHistory(
                request.getAccountId(),
                PageRequest.of(request.getPageNumber(), request.getPageSize()));
        GetTransactionsResponse response = new GetTransactionsResponse();
        response.setTransactions(transactionsPage.getContent());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "transferFundsRequest")
    @ResponsePayload
    public TransferFundsResponse transferFunds(@RequestPayload TransferFundsRequest request) {
        accountService.transferFunds(
                request.getCreditor(),
                request.getDebtor(),
                request.getAmount(),
                request.getCurrency()
        );
        return new TransferFundsResponse();
    }
}

