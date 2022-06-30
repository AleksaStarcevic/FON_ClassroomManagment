package com.example.fon_classroommanagment.Services;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.fon_classroommanagment.Configuration.ExceptionMessages;
import com.example.fon_classroommanagment.Exceptions.TokenNotFaundException;
import com.example.fon_classroommanagment.Exceptions.UserExistsExcetion;
import com.example.fon_classroommanagment.Models.Emplayee.Employee;
import com.example.fon_classroommanagment.Models.User.Account;
import com.example.fon_classroommanagment.Models.User.UserProfile;
import com.example.fon_classroommanagment.Models.User.UserRole;
import com.example.fon_classroommanagment.Models.User.ValidationToken;
import com.example.fon_classroommanagment.Repository.AccountRepository;
import com.example.fon_classroommanagment.Repository.TokenValidationAccountRepository;
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
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ValidationToken  createValidationToken(Account dto) throws  UserExistsExcetion{
        String token=UUID.randomUUID().toString();
        if(employeeService.findByEmail(dto.getEmail())==null) throw new UserExistsExcetion("user ne postoji");
        if(userService.findByEmail(dto.getEmail())!=null) throw new UserExistsExcetion("user  je vec registrovan");

        EncodePassword(dto);
        ValidationToken validationToken;

        ValidationToken existing = tokenValidationAccountRepository.findByRegisterDTO(dto);
        if(existing!=null){
            validationToken=new ValidationToken(existing.getToken(),dto);
        }
        else{
             validationToken=new ValidationToken(token,dto);
        }
        SaveToken(validationToken);
        return  validationToken;

    }

    private void SaveToken(ValidationToken validationToken) {
        tokenValidationAccountRepository.save(validationToken);
    }

    private void EncodePassword(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));

    }

    public void ConfirmAccount(String token) throws TokenNotFaundException,TokenExpiredException {
        Optional<ValidationToken> Opt_validationToken=tokenValidationAccountRepository.findById(token);

        if(Opt_validationToken.isEmpty()) throw new TokenNotFaundException(ExceptionMessages.TOKEN_REGISTRATION_NOT_FOUND_MESSAGE);

        ValidationToken validationToken=Opt_validationToken.get();

        if(validationToken.isExpired()) {
            accountRepository.deleteById(validationToken.getRegisterDTO().getEmail());
            throw  new TokenExpiredException(ExceptionMessages.TOKEN_REGISTRATION_EXPIRED_MESSAGE);
        }
        Account account=accountRepository.findByEmail(validationToken.getRegisterDTO().getEmail());

        UserRole simpleUserRole=new UserRole(1L,"USER");
        Employee employee=employeeService.findByEmail(account.getEmail());
        UserProfile user=new UserProfile(UUID.randomUUID(),account.getEmail(),account.getPassword(),simpleUserRole,employee);

        tokenValidationAccountRepository.delete(validationToken);
        userService.saveUser(user);
    }


}
