package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbthymeleafwinter.entity.Product;
import ru.gb.gbthymeleafwinter.entity.enums.RegistrationResult;
import ru.gb.gbthymeleafwinter.entity.security.UserFromRegForm;
import ru.gb.gbthymeleafwinter.service.AuthenticationService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authorizeRefPage")
public class LoginController {

    private final AuthenticationService authenticationService;



    @GetMapping
    public String authorizeReferencePage() {
        return "authorize-reference";
    }


    @GetMapping("/registrationForm")
    public String showRegistrationForm(Model model) {
        UserFromRegForm userFromRegForm = new UserFromRegForm();
        model.addAttribute("userFromRegForm", userFromRegForm);
        return "registration-form";
    }

    @PostMapping("/registrationForm")
    public String registration(Model model, UserFromRegForm userFromRegForm) {
            RegistrationResult registrationResult = authenticationService.save(userFromRegForm);
            String checkString = RegistrationResult.REG_OK.getMessage();
            model.addAttribute("regResult", registrationResult);
            model.addAttribute("checkString", checkString);
        return "registration-result";
    }

}
