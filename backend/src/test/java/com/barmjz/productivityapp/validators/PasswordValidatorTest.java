package com.barmjz.productivityapp.validators;

import com.barmjz.productivityapp.user.registration.validators.EmailValidator;
import com.barmjz.productivityapp.user.registration.validators.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {
    private PasswordValidator passwordValidator;

    @BeforeEach
    void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    void acceptsValidPassword(){
        // Given
        String password = "ImA!@#$%^&*()-__+.VeryGood_Password735";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isTrue();
    }
    @Test
    void rejectsPasswordWithInvalidCharacter(){
        // Given
        String password = "Password_125;";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsPasswordWithLessThan8Chars(){
        // Given
        String password = "Pa1_3@";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsPasswordNoNumbers(){
        // Given
        String password = "Party_!@#$";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsPasswordNoSpecialChars(){
        // Given
        String password = "Party12345";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsPasswordNoUpperCaseChars(){
        // Given
        String password = "party12_!@#$";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsPasswordNoLowerCaseChars(){
        // Given
        String password = "PARTY12_!@#$";
        // When
        boolean result = passwordValidator.test(password);
        // Then
        assertThat(result).isFalse();
    }
}
