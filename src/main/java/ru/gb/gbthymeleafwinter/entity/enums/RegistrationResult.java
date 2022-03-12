package ru.gb.gbthymeleafwinter.entity.enums;

public enum RegistrationResult {

    REG_OK("Регистрация прошла успешно. Нажмите на кнопку ниже, чтобы авторизоваться."),
    REPEATED_PASSWORD("Вы ввели разные пароли. Повторите попытку."),
    USER_EXISTS("Пользователь с таким именем уже существует. Используйте другое имя пользователя."),
    EMPTY_FIELD("Заполнены не все поля регистрационной формы.");

    private String message;

    RegistrationResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
