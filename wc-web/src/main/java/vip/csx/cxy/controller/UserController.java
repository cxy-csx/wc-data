//package vip.csx.cxy.controller;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import vip.csx.cxy.common.R;
//import vip.csx.cxy.service.UserService;
//
//@RestController
//@RequestMapping("user")
//@Slf4j
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("getUserInfo")
//    public R getUserInfo(Boolean refresh){
//        return userService.getUserInfo(refresh);
//    }
//}
