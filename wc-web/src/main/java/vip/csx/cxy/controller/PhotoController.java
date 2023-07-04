package vip.csx.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.csx.cxy.common.R;
import vip.csx.cxy.message.req.ReqOrder;
import vip.csx.cxy.service.OrderService;
import vip.csx.cxy.service.PhotoService;

@RestController
@RequestMapping("photo")
public class PhotoController {


    @Autowired
    private PhotoService photoService;

    @PostMapping("/create")
    public R createPhoto() {
        return photoService.createPhoto();
    }


}
