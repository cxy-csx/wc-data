package vip.csx.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.csx.cxy.common.R;
import vip.csx.cxy.service.UserCourseService;

@RestController
@RequestMapping("userCourse")
public class UserCourseController {


    @Autowired
    private UserCourseService userCourseService;

    @GetMapping("list")
    public R geUserCourseList() {
        return userCourseService.geUserCourseList();
    }
}

