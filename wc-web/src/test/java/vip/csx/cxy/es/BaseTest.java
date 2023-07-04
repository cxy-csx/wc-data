package vip.csx.cxy.es;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONString;
import cn.hutool.json.JSONUtil;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import vip.csx.cxy.service.PhotoService;
import vip.csx.cxy.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class BaseTest {

    @Autowired
    ProductRepository orderRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    UserService userService;


    @Autowired
    PhotoService photoService;


    @Test
    public void test() throws IOException {

//        userService.test();

//        System.out.println(orderRepository.findById("S1xvB4kBgYZUySVCoBbc").orElse(null));






        photoService.createPhoto();


    }


}
