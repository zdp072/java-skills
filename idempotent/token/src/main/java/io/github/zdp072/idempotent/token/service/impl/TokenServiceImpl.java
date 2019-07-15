package io.github.zdp072.idempotent.token.service.impl;

import io.github.zdp072.idempotent.token.common.Constant;
import io.github.zdp072.idempotent.token.common.ResponseCode;
import io.github.zdp072.idempotent.token.common.ServerResponse;
import io.github.zdp072.idempotent.token.exception.ServiceException;
import io.github.zdp072.idempotent.token.service.TokenService;
import io.github.zdp072.idempotent.token.util.JedisUtil;
import io.github.zdp072.idempotent.token.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;

import javax.servlet.http.HttpServletRequest;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAME = "token";

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public ServerResponse createToken() {
        String str = RandomUtil.UUID32();
        StrBuilder token = new StrBuilder();
        token.append(Constant.Redis.TOKEN_PREFIX).append(str);

        jedisUtil.set(token.toString(), token.toString(), Constant.Redis.EXPIRE_TIME_MINUTE);

        return ServerResponse.success(token.toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isBlank(token)) {
                throw new ServiceException(ResponseCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        if (!jedisUtil.exists(token)) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }

        Long del = jedisUtil.del(token);
        if (del <= 0) {
            throw new ServiceException(ResponseCode.REPETITIVE_OPERATION.getMsg());
        }
    }

}
