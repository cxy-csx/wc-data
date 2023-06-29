package vip.csx.cxy.message.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
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
    //验证码
    private String code;

    public void from(WxUserInfo wxUserInfo) {
        this.nickname = wxUserInfo.getNickName();
        this.avatar = wxUserInfo.getAvatarUrl();
        this.username = "";
        this.password = "";
        this.phone = "";
        this.gender = wxUserInfo.getGender();
        this.openId = wxUserInfo.getOpenId();
    }
}
