package vip.csx.cxy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import vip.csx.cxy.annotation.NoAuth;
import vip.csx.cxy.common.CodeConstant;
import vip.csx.cxy.common.CommonPage;
import vip.csx.cxy.common.R;
import vip.csx.cxy.entity.Course;
import vip.csx.cxy.entity.UserCourse;
import vip.csx.cxy.es.Product;
import vip.csx.cxy.es.ProductRepository;
import vip.csx.cxy.mapper.CourseMapper;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    ProductRepository orderRepository;

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    RestHighLevelClient restHighLevelClient;



    /**
     * 获取课程列表
     * @param start 起始位置
     * @return
     */
    public R list(Integer start) {

        System.out.println(orderRepository);

        Product product = orderRepository.findById("UFz7B4kBgYZUySVCxBYY").orElse(null);

        System.out.println(product);


        System.out.println(restHighLevelClient);
        System.out.println(elasticsearchRestTemplate);
        System.out.println(orderRepository);

        IPage<Course> page = new Page<>(start, CodeConstant.PAGE_SIZE);
        IPage<Course> courseIPage = courseMapper.selectPage(page, null);
        CommonPage<Course> courseCommonPage = CommonPage.restPage(courseIPage);
        return R.SUCCESS(courseCommonPage);

    }

    public R getCarousel() {
        LambdaQueryWrapper<Course> queryWrapper = Wrappers.<Course>lambdaQuery().gt(Course::getIsCarousel, 0).orderByAsc(Course::getIsCarousel);
        queryWrapper.select(Course::getId,Course::getCarouselUrl);
        List<Course> coursewareList = courseMapper.selectList(queryWrapper);
        return R.SUCCESS(coursewareList);
    }

    /**
     * 获取用户课程信息
     */
    public Course getCourseById(Integer courseId){
        Course course = courseMapper.selectOne(
                Wrappers.<Course>lambdaQuery()
                        .eq(Course::getId, courseId));
        return course;
    }
}
