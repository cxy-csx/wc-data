package vip.csx.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.csx.cxy.annotation.NoAuth;
import vip.csx.cxy.common.R;
import vip.csx.cxy.service.CourseService;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取课程列表
     * @param start
     * @return
     */
    @GetMapping("list")
    @NoAuth
    public R list(@RequestParam Integer start){
        return courseService.list(start);
    }

    /**
     * 获取课程封面图片
     * @return
     */
    @GetMapping("/getCarousel")
    @NoAuth
    public R getCarousel() {
        return courseService.getCarousel();
    }


}
