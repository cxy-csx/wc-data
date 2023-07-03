package vip.csx.cxy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderSn;

    private Integer courseId;

    private Long userId;

    private BigDecimal price;

    private LocalDateTime createTime;

    private LocalDateTime payTime;

    private Boolean isPay;

    private Integer payType;

    private String wxOrder;
}
