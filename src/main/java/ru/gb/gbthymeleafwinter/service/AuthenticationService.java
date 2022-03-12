package ru.gb.gbthymeleafwinter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.gbthymeleafwinter.dao.security.AccountRoleDao;
import ru.gb.gbthymeleafwinter.dao.security.AccountUserDao;
import ru.gb.gbthymeleafwinter.entity.enums.RegistrationResult;
import ru.gb.gbthymeleafwinter.entity.security.AccountRole;
import ru.gb.gbthymeleafwinter.entity.security.AccountUser;
import ru.gb.gbthymeleafwinter.entity.security.UserFromRegForm;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountUserDao accountUserDao;
    private final AccountRoleDao accountRoleDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public RegistrationResult save(UserFromRegForm userFromRegForm) {
        if(userFromRegForm.getUsername() != null && userFromRegForm.getPassword() != null
                && userFromRegForm.getFirstname() != null && userFromRegForm.getLastname() != null
                && userFromRegForm.getUsername() != "" && userFromRegForm.getPassword() != ""
                && userFromRegForm.getFirstname() != "" && userFromRegForm.getLastname() != "") {
            if (!userFromRegForm.getPassword().equals(userFromRegForm.getRepeatedPassword())) {
                return RegistrationResult.REPEATED_PASSWORD;
            }
            Optional<AccountUser> accountUserFromDbOptional = accountUserDao.findByUsername(userFromRegForm.getUsername());
            if (accountUserFromDbOptional.isEmpty()) {
                AccountUser newAccountUser = new AccountUser();
                newAccountUser.setUsername(userFromRegForm.getUsername());
                newAccountUser.setPassword(bCryptPasswordEncoder.encode(userFromRegForm.getPassword()));
                newAccountUser.setFirstname(userFromRegForm.getFirstname());
                newAccountUser.setLastname(userFromRegForm.getLastname());
                Optional<AccountRole> accountRoleFromDb = accountRoleDao.findById(1L);
                if(accountRoleFromDb.isPresent()) {
                    newAccountUser.setRoles(Set.of(accountRoleFromDb.get()));
                }
                accountUserDao.save(newAccountUser);
                return RegistrationResult.REG_OK;
            } else {
                return RegistrationResult.USER_EXISTS;
            }
        } else {
            return RegistrationResult.EMPTY_FIELD;
        }
    }


}
