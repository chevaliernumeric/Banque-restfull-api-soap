package com.example.bank_api.mapper;

import com.example.bank_api.model.Transaction;
import com.example.bank_api.wsdl.TransactionDto;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        // Map other necessary fields
        return transactionDto;
    }

    public static List<TransactionDto> mapToTransactionDtoList(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionDto)
                .collect(Collectors.toList());
    }
}
