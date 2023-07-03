package vip.csx.cxy.service;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vip.csx.cxy.common.R;
import vip.csx.cxy.common.StrConstant;
import vip.csx.cxy.entity.Course;
import vip.csx.cxy.entity.Order;
import vip.csx.cxy.entity.UserCourse;
import vip.csx.cxy.mapper.OrderMapper;
import vip.csx.cxy.message.dto.UserDto;
import vip.csx.cxy.message.req.ReqOrder;
import vip.csx.cxy.thread.UserThreadLocal;

import java.time.LocalDateTime;

@Service
public class OrderService {

    @Autowired
    private UserCourseService userCourseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrderMapper orderMapper;


    @Transactional
    public R createOrder(ReqOrder req) {
        UserDto userDto = UserThreadLocal.get();
        UserCourse userCourse = userCourseService.getUserCourseById(req.getCourseId());

        if (userCourse== null) {
            Order order = new Order();
            order.setCreateTime(LocalDateTime.now());

            Course course = courseService.getCourseById(req.getCourseId());
            order.setOrderSn(UUID.randomUUID().toString());
            order.setPrice(course.getPrice());
            order.setUserId(userDto.getId());

            orderMapper.insert(order);
//            JSONObject jsonObject = WxPay.minAppPay(order.getOrderSn(), "" + order.getPrice(), mchid, "购买课件ID为:" + order.getCwId(), "码神课件", null, "http://a4tuaki.nat.ipyingshe.com/cwApi/order/callback", null, "0", null, key);
//            return R.SUCCESS(jsonObject);
            return R.SUCCESS();
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(StrConstant.MESSAGE_HEADER, "已经购买请勿重复购买");
            return R.SUCCESS(jsonObject);
        }
    }

}
