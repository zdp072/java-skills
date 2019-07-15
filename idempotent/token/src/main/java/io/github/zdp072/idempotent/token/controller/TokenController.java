package io.github.zdp072.idempotent.token.controller;

import io.github.zdp072.idempotent.token.common.ServerResponse;
import io.github.zdp072.idempotent.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ServerResponse token() {
        return tokenService.createToken();
    }

}