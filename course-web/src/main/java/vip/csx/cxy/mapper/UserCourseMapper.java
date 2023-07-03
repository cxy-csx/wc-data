package vip.csx.cxy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.csx.cxy.entity.UserCourse;
import vip.csx.cxy.message.dto.UserCourseDto;

import java.util.List;

public interface UserCourseMapper extends BaseMapper<UserCourse> {
    List<UserCourseDto> geUserCourseList(Long id);
}
