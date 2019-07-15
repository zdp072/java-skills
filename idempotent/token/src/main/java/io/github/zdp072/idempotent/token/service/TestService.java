package io.github.zdp072.idempotent.token.service;

import io.github.zdp072.idempotent.token.common.ServerResponse;

public interface TestService {

    ServerResponse testIdempotence();

}