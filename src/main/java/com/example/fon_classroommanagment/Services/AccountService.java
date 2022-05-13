package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Models.DTO.AccountDTO;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Models.User.UserRole;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Repository.AccountRepository;
import com.example.fon_classroommanagment.Repository.TokenValidationAccountRepository;
import com.example.fon_classroommanagment.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private TokenValidationAccountRepository tokenValidationAccountRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    public ValidationToken  createValidationToken(Account account,String token){
        ValidationToken validationToken=new ValidationToken();

        System.out.println(account);
        validationToken.setToken(token);
        validationToken.setAccount(account);
        tokenValidationAccountRepository.save(validationToken);
        return  validationToken;

    }
    public boolean ConfirmAccount(String token){
        Optional<ValidationToken> Opt_validationToken=tokenValidationAccountRepository.findById(token);
        if(Opt_validationToken.isEmpty()) return false;
        ValidationToken validationToken=Opt_validationToken.get();
        if(validationToken.isExpired) return false;
        Account account=accountRepository.findByEmail(validationToken.getAccount().getEmail());
        System.out.println(account);
        UserRole simpleUserRole=new UserRole(1L,"USER");
        Employee employee=new Employee(account.getFirstName(),account.getLastName(),account.getDepartment(),account.getTitle(),account.getType());
        UserProfile user=new UserProfile(UUID.randomUUID(),account.getEmail(),account.getPassword(),simpleUserRole,employee);
        tokenValidationAccountRepository.delete(validationToken);
        return userService.saveUser(user);
    }


}
