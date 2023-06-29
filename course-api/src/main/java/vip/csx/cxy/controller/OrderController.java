package vip.csx.cxy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.csx.cxy.common.R;
import vip.csx.cxy.entity.Order;
import vip.csx.cxy.message.req.ReqOrder;
import vip.csx.cxy.service.OrderService;

@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public R createOrder(@RequestBody ReqOrder req) {
        return orderService.createOrder(req);
    }

}
