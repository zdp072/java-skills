package io.github.zdp072.idempotent.token.service.impl;

import io.github.zdp072.idempotent.token.common.ServerResponse;
import io.github.zdp072.idempotent.token.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

}