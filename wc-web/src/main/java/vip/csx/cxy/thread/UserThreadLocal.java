package vip.csx.cxy.thread;

import vip.csx.cxy.message.dto.UserDto;

public class UserThreadLocal {
    private static final ThreadLocal<UserDto> LOCAL = new ThreadLocal<>();

    public static void put(UserDto userDto){
        LOCAL.set(userDto);
    }

    public static UserDto get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
