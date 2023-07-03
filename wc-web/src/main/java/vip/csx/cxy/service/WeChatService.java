package vip.csx.cxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import vip.csx.cxy.common.CodeConstant;
import vip.csx.cxy.common.CommonPage;
import vip.csx.cxy.common.R;
import vip.csx.cxy.entity.Course;
import vip.csx.cxy.entity.WeChatArticle;
import vip.csx.cxy.mapper.WeChatArticleMapper;

import java.util.List;

@Service
public class WeChatService {

    @Autowired
    private WeChatArticleMapper weChatArticleMapper;


    /**
     * 获取微信公众号文章
     * @return
     */
    public R getArticleList(Integer start) {
        IPage<WeChatArticle> page = new Page<>(start, CodeConstant.PAGE_SIZE);
        IPage<WeChatArticle> courseIPage = weChatArticleMapper.selectPage(page, null);
        CommonPage<WeChatArticle> commonPage = CommonPage.restPage(courseIPage);
        return R.SUCCESS(commonPage);
    }
}
