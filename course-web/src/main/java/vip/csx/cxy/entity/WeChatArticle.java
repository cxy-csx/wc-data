package vip.csx.cxy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("wechat_article_list")
public class WeChatArticle {

    private String title;


    private String url;


    private String author;


    private String cover;


    private String publishTime;

}
