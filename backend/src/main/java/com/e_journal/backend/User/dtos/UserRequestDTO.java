package com.e_journal.backend.User.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDTO {

    @NotBlank(message = "Missign username")
    @NotNull(message = "Invalid username: Username is NULL")
    private String username;

    @NotBlank(message = "Missign password")
    @NotNull(message = "Invalid password: Password is NULL")
    private String password;

    @NotBlank(message = "Missign firstname")
    @NotNull(message = "Invalid firstname: Firstname is NULL")
    private String firstName;


    private String lastName;

    @NotBlank(message = "Missign lastname")
    @NotNull(message = "Invalid lastname: Lastname is NULL")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,7}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Not valid email")
    String email;
}
