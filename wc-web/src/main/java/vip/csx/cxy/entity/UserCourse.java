package vip.csx.cxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCourse {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private Integer courseId;

    private LocalDateTime createTime;
}
