package com.example.bank_api.controller;


import com.example.bank_api.dto.TransferRequest;
import com.example.bank_api.model.Transaction;
import com.example.bank_api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccountBalance(accountId));
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<Page<Transaction>> getTransactionHistory(@PathVariable Long accountId,
                                                                   @RequestParam int page,
                                                                   @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(accountService.getTransactionHistory(accountId, pageable));
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<Void> transfer(@PathVariable Long accountId,
                                         @RequestBody TransferRequest transferRequest) {
        accountService.transferFunds(
                transferRequest.getCreditor(),
                transferRequest.getDebtor(),
                transferRequest.getAmount(),
                transferRequest.getCurrency()
        );
        return ResponseEntity.ok().build();
    }

}

