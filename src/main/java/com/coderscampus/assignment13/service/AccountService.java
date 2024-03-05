package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepo;

    public Account save(Account account) {
        return accountRepo.save(account);
    }
//    public void deleteAccount(Account account) {
//        accountRepo.delete(account);
//    }

    public Account findById(Long accountId) {
        Optional<Account> accountOpt = accountRepo.findById(accountId);
        return accountRepo.findById(accountId).orElse(new Account());
    }
}
