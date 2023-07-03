package vip.csx.cxy.service.wx;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import vip.csx.cxy.common.R;
import vip.csx.cxy.common.StrConstant;
import vip.csx.cxy.entity.User;
import vip.csx.cxy.message.dto.UserDto;
import vip.csx.cxy.message.dto.WxUserInfo;
import vip.csx.cxy.message.req.ReqWxAuth;
import vip.csx.cxy.message.resp.RespUser;
import vip.csx.cxy.service.UserService;
import vip.csx.cxy.utils.JWTUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class WxAuthorService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Value("${wx-mini.secret}")
    private String secret;
    @Value("${wx-mini.appid}")
    private String appid;

    @Value("${wx-mini.url}")
    private String url;

    @Autowired
    private WxService wxService;

    @Autowired
    private UserService userService;

    public String getSessionId(String code) {

        String fullUrl = StrUtil.format(url, code);
        System.out.println(fullUrl);
        String res = HttpUtil.get(fullUrl);
        String uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(StrConstant.WX_SESSION_KEY + uuid, res);
        return uuid;
    }

    public R authLogin(ReqWxAuth req) {
        try {
            String wxRes = wxService.wxDecrypt(req.getEncryptedData(), req.getSessionId(), req.getIv());
            WxUserInfo wxUserInfo = JSON.parseObject(wxRes, WxUserInfo.class);
            User user = userService.getUserByOpenId(wxUserInfo.getOpenId());
            UserDto userDto = new UserDto();
            if (!Objects.isNull(user)) {
                //登录成功
                BeanUtils.copyProperties(user, userDto);
                return R.SUCCESS(this.login(userDto));
            } else {
                userDto.from(wxUserInfo);
                return this.register(userDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.FAIL();
    }


    private RespUser login(UserDto userDto) {
        RespUser resp = new RespUser();
        BeanUtils.copyProperties(userDto, resp);
        // 登录成功 封装用户信息到token
        resp.setPassword(null);
        resp.setUsername(null);
        resp.setOpenId(null);
        String token = JWTUtils.sign(userDto.getId());
        resp.setToken(token);
        // 保存到redis内
        redisTemplate.opsForValue().set(StrConstant.WX_TOKEN_KEY + token, JSON.toJSONString(userDto), 7, TimeUnit.DAYS);
        return resp;
    }

    private R register(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User queryUser = userService.getUserByOpenId(user.getOpenId());
        if (Objects.isNull(queryUser)) {
            userService.insertUser(user);
        }
        return R.SUCCESS(login(userDto));
    }
}
