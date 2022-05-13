package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Repository.AccountRepository;
import com.example.fon_classroommanagment.Repository.TokenValidationAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private TokenValidationAccountRepository repository;
    @Autowired
    private AccountRepository accountRepository;

    public ValidationToken  createValidationToken(AccountDTO accountDTO,String token){
        ValidationToken validationToken=new ValidationToken();
        validationToken.setEmail(accountDTO.getEmail());
        validationToken.setToken(token);
        repository.save(validationToken);
        return  validationToken;

    }
    public void SaveAccount(Account account){
       accountRepository.save(account);
    }

}
