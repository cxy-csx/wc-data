package vip.csx.cxy.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import vip.csx.cxy.entity.Course;

import java.time.LocalDateTime;

@Data
public class UserCourseDto {
    private Integer id;
    private Integer userId;
    private Course course;
    private Integer courseId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
