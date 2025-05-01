package kg.attractor.edufood.annotations.impl;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kg.attractor.edufood.annotations.ValidEmail;
import kg.attractor.edufood.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.checkEmailForUniquness(email);
    }
}
