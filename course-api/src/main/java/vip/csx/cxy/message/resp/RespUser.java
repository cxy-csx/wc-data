package vip.csx.cxy.message.resp;

import lombok.Data;
import vip.csx.cxy.message.dto.WxUserInfo;

import java.util.List;

@Data
public class RespUser {
    private Long id;

    private String nickname;

    private String username;

    private String password;

    private String gender;

    private String avatar;

    private String phone;

    private String openId;

    // 拓展属性
    private String token;
    List<String> permissions;
    List<String> roles;
}
