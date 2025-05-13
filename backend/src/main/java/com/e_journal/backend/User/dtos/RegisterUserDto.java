package com.e_journal.backend.User.dtos;

import com.e_journal.backend.Auth.services.validators.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterUserDto {
    @NotBlank(message = "Missign username")
    @NotNull(message = "Invalid username: Username is NULL")
    private String username;

    @NotBlank(message = "Missign password")
    @NotNull(message = "Invalid password: Password is NULL")
    @ValidPassword
    private String password;

    @NotBlank(message = "Missign firstname")
    @NotNull(message = "Invalid firstname: Firstname is NULL")
    private String firstName;

    @NotBlank(message = "Missign lastname")
    @NotNull(message = "Invalid lastname: Lastname is NULL")
    private String lastName;

    @NotBlank(message = "Missign email")
    @NotNull(message = "Invalid email: Email is NULL")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,7}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Not valid email")
    String email;

}
