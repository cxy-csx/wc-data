package vip.csx.cxy.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import vip.csx.cxy.common.CodeConstant;
import vip.csx.cxy.common.R;
import vip.csx.cxy.common.StrConstant;
import vip.csx.cxy.entity.User;
import vip.csx.cxy.mapper.UserMapper;
import vip.csx.cxy.message.dto.UserDto;
import vip.csx.cxy.thread.UserThreadLocal;
import vip.csx.cxy.utils.JWTUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    public User getUserByOpenId(String openId) {
        return userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId));
    }

    public Long insertUser(User user) {
        if (!Objects.isNull(user)) {
            userMapper.insert(user);
            return user.getId();
        }
        return null;
    }

    public R getUserInfo(Boolean refresh) {
        UserDto userDto = UserThreadLocal.get();
        if (refresh) {
            String token = JWTUtils.sign(userDto.getId());
            userDto.setToken(token);
            redisTemplate.opsForValue().set(StrConstant.WX_TOKEN_KEY + token, JSON.toJSONString(userDto), CodeConstant.WEEK, TimeUnit.DAYS);
        }
        return R.SUCCESS(userDto);
    }

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    public void test(){
        System.out.println(restHighLevelClient);

    }
}
