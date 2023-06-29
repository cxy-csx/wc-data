package vip.csx.cxy.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import vip.csx.cxy.annotation.NoAuth;
import vip.csx.cxy.common.R;
import vip.csx.cxy.common.StrConstant;
import vip.csx.cxy.message.dto.UserDto;
import vip.csx.cxy.thread.UserThreadLocal;
import vip.csx.cxy.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return Boolean.TRUE;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if(handlerMethod.hasMethodAnnotation(NoAuth.class)){
            return Boolean.TRUE;
        }

        String token = request.getHeader(StrConstant.WX_HERDER_IDENTIFICATION);
        if (StringUtils.isBlank(token)){
            return this.noLoginResponse(response);
        }
        token = token.replace(StrConstant.WX_TOKEN_HEADER, "");
        boolean verify = JWTUtils.verify(token);
        if (!verify){
            return this.noLoginResponse(response);
        }
        String userJson = redisTemplate.opsForValue().get(StrConstant.WX_TOKEN_KEY + token);
        if (StringUtils.isBlank(userJson)){
            return noLoginResponse(response);
        }
        UserDto userDto = JSON.parseObject(userJson, UserDto.class);
        UserThreadLocal.put(userDto);
        return Boolean.TRUE;
    }

    private boolean noLoginResponse(HttpServletResponse response) throws IOException {
        response.setContentType(StrConstant.MIME_JSON);
        response.getWriter().write(JSON.toJSONString(R.FAIL(StrConstant.NOT_LOGIN)));
        return Boolean.FALSE;
    }

}
