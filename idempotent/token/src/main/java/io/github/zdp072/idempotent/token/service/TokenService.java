package io.github.zdp072.idempotent.token.service;

import io.github.zdp072.idempotent.token.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    /**
     * 创建token
     * @return
     */
    ServerResponse createToken();

    /**
     * 校验token
     * @param request
     */
    void checkToken(HttpServletRequest request);

}