package com.e_journal.backend.Auth.services.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {}

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // min and max length
                new LengthRule(8,30),
                // minimal Upper case character
                new CharacterRule(EnglishCharacterData.UpperCase,1),
                // minimal Lower case character
                new CharacterRule(EnglishCharacterData.LowerCase,1),
                // minimal Digits
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // minimal special symbols
                new CharacterRule(EnglishCharacterData.Special,1),

                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        for (String msg : validator.getMessages(result)) {
            context.buildConstraintViolationWithTemplate(msg)
                    .addConstraintViolation();
        }
        return false;

    }
}
