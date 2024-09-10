package com.example.bank_api.service;

import com.example.bank_api.model.Account;
import com.example.bank_api.model.Transaction;
import com.example.bank_api.repository.AccountRepository;
import com.example.bank_api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    // Method to retrieve account balance
    public Double getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte non trouvé: " + accountId));
        return account.getBalance();
    }

    // Method to transfer funds between accounts
    public void transferFunds(Long fromAccountId, Long toAccountId, Double amount, String currency) {
        // Retrieve accounts by ID
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found with ID: " + fromAccountId));
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found with ID: " + toAccountId));

        // Ensure there are sufficient funds in the source account
        if (fromAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds in source account");
        }

        // Deduct amount from the source account and add to the destination account
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        // Record the debit transaction for the source account
        Transaction debitTransaction = new Transaction();
        debitTransaction.setAccount(fromAccount);
        debitTransaction.setType("Debit");
        debitTransaction.setAmount(amount);
        debitTransaction.setCurrency(currency);
        debitTransaction.setTransactionDate(LocalDateTime.now()); // Use setTransactionDate instead of setTimestamp
        transactionRepository.save(debitTransaction);

        // Record the credit transaction for the destination account
        Transaction creditTransaction = new Transaction();
        creditTransaction.setAccount(toAccount);
        creditTransaction.setType("Credit");
        creditTransaction.setAmount(amount);
        creditTransaction.setCurrency(currency);
        creditTransaction.setTransactionDate(LocalDateTime.now()); // Use setTransactionDate instead of setTimestamp
        transactionRepository.save(creditTransaction);

        // Save the updated balances of both accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    // Method to retrieve transaction history with pagination support
    public Page<Transaction> getTransactionHistory(Long accountId, Pageable pageable) {
        return transactionRepository.findByAccountId(accountId, pageable);
    }
}
