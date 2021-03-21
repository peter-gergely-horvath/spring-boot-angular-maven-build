package com.frakton.commands.auth;

import org.commandmosaic.security.jwt.core.TokenProvider;
import org.commandmosaic.security.jwt.spring.command.SpringUsernamePasswordAuthenticationCommand;
import org.springframework.security.authentication.AuthenticationManager;

public class LoginCommand extends SpringUsernamePasswordAuthenticationCommand {

    public LoginCommand(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        super(tokenProvider, authenticationManager);
    }
}
