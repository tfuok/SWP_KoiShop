package com.example.demo.repository;

import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    //tìm account = email
    Account findAccountByEmail(String email);

    Account findAccountById(long id);
}
