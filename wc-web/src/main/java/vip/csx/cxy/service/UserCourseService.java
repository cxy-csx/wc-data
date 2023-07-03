package vip.csx.cxy.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import vip.csx.cxy.common.R;
import vip.csx.cxy.entity.UserCourse;
import vip.csx.cxy.mapper.UserCourseMapper;
import vip.csx.cxy.message.dto.UserCourseDto;
import vip.csx.cxy.message.dto.UserDto;
import vip.csx.cxy.thread.UserThreadLocal;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserCourseService {

    @Resource
    private UserCourseMapper userCourseMapper;

    public R geUserCourseList() {
        UserDto userDto = UserThreadLocal.get();
        Long id = userDto.getId();
        List<UserCourseDto> list = userCourseMapper.geUserCourseList(id);
        return R.SUCCESS(list);
    }

    /**
     * 获取用户课程信息
     */
    public UserCourse getUserCourseById(Integer courseId) {
        UserDto userDto = UserThreadLocal.get();

        UserCourse userCourse = userCourseMapper.selectOne(
                Wrappers.<UserCourse>lambdaQuery()
                        .eq(UserCourse::getUserId, userDto.getId())
                        .eq(UserCourse::getCourseId, courseId)
        );

        return userCourse;
    }

}
