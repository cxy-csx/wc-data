package vip.csx.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.csx.cxy.annotation.NoAuth;
import vip.csx.cxy.common.R;
import vip.csx.cxy.service.UserService;
import vip.csx.cxy.service.WeChatService;

@RestController
@RequestMapping("wechat")
public class WeChatController {

    @Autowired
    private WeChatService wechatService;

    @GetMapping("getArticleList")
//    @NoAuth
    public R getArticleList(@RequestParam Integer start){
        return wechatService.getArticleList(start);
    }
}
