package com.example.service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> register(Account account) {
        if (account.getUsername() == null || account.getUsername().isBlank()) return Optional.empty();
        if (account.getPassword() == null || account.getPassword().length() < 4) return Optional.empty();
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) return Optional.of(new Account());

        return Optional.of(accountRepository.save(account));
    }

    public Optional<Account> login(Account input) {
        return accountRepository.findByUsername(input.getUsername())
                .filter(acc -> acc.getPassword().equals(input.getPassword()));
    }
}
