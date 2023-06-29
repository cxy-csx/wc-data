package vip.csx.cxy.es;

import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import vip.csx.cxy.service.UserService;

import javax.annotation.Resource;
import java.io.IOException;

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


    @Test
    public void test() throws IOException {

        System.out.println(elasticsearchRestTemplate);

        System.out.println(orderRepository);

        System.out.println(restHighLevelClient);

        userService.test();

//        System.out.println(orderRepository.findById("S1xvB4kBgYZUySVCoBbc").orElse(null));


    }


}
