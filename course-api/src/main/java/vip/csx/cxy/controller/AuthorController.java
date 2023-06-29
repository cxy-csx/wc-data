package vip.csx.cxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.csx.cxy.annotation.NoAuth;
import vip.csx.cxy.common.R;
import vip.csx.cxy.message.req.ReqWxAuth;
import vip.csx.cxy.service.AuthorService;

import java.util.HashMap;

@RestController
@RequestMapping("author")
@Slf4j
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @GetMapping("/getSessionId")
    @NoAuth
    public R getSessionId(@RequestParam String code) {
        String sessionId = authorService.getSessionId(code);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("sessionId", sessionId);
        return R.SUCCESS(hashMap);
    }

    @PostMapping("/authLogin")
    @NoAuth
    public R authLogin(@RequestBody ReqWxAuth req) {
        R result = authorService.authLogin(req);
        log.info("{}",result);
        return result;
    }

}
