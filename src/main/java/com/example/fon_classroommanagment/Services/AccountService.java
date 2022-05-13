package com.example.fon_classroommanagment.Services;

import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ValidationToken  createValidationToken(Account account,String token) throws  UserExistsExcetion{
        if(userService.findByEmail(account.getEmail())!=null) throw new UserExistsExcetion("user vec postoji");
        ValidationToken validationToken=new ValidationToken(token,account);
        EncodePassword(account);

       SaveToken(validationToken);
        return  validationToken;

    }

    private void SaveToken(ValidationToken validationToken) {
        tokenValidationAccountRepository.save(validationToken);
    }

    private void EncodePassword(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));

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
