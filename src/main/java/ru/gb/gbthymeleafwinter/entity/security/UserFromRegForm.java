package ru.gb.gbthymeleafwinter.entity.security;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserFromRegForm {

    String username;
    String password;
    String repeatedPassword;
    String firstname;
    String lastname;


}
