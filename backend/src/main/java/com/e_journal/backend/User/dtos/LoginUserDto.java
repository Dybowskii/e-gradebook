package com.e_journal.backend.User.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDto {
    private String username;

    private String password;
}
