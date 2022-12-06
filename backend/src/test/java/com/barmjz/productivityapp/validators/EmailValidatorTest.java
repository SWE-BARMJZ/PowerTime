package com.barmjz.productivityapp.validators;

import com.barmjz.productivityapp.user.registration.validators.EmailValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeEach
    void setUp() {
        emailValidator = new EmailValidator();
    }

    @Test
    void acceptsValidEmail(){
        // Given
        String email = "84-Du.M.my_124@Domain.com";
        // When
        boolean result = emailValidator.test(email);
        // Then
        assertThat(result).isTrue();
    }

    @Test
    void rejectsEmailWithoutAtSign(){
        // Given
        String email = "84-DuMmy_124Domain.com";
        // When
        boolean result = emailValidator.test(email);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsEmailWithDotAtStart(){
        // Given
        String email = ".dummy@gmail.com";
        // When
        boolean result = emailValidator.test(email);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsEmailWithConsecutiveDots(){
        // Given
        String email = "dummy@gmail..com";
        // When
        boolean result = emailValidator.test(email);
        // Then
        assertThat(result).isFalse();
    }
    @Test
    void rejectsEmailWithTooManyChars(){
        // Given
        String email = "dummy50813750137501730583175081375013750817385713857810375018513735013751087508173508137508175081750317308517085173085710387510875081750813759640761093256-165108725617-265-127-5256792359236592365792566279560137508173508173850713805710875310875801375081705817350813857@gmail.com";
        // When
        boolean result = emailValidator.test(email);
        // Then
        assertThat(result).isFalse();
    }
}
