package vip.csx.cxy.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;
import vip.csx.cxy.common.R;
import vip.csx.cxy.message.req.ReqOrder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoService {

    @Autowired
    private OkHttpClient okHttpClient;


    public R createPhoto() {

        String imgUrl = "https://github.com/sczhou/CodeFormer/raw/master/assets/restoration_result1.png";

        String uuid = this.generatePicture(imgUrl);
        String outputUrl = this.getOutputUrl(uuid);

        return R.SUCCESS(outputUrl);

    }

    private String getOutputUrl(String uuid) {
        String outPutUrl = "";

        String url = "https://replicate.com/api/models/sczhou/codeformer/versions/7de2ea26c616d5bf2245ad0d5e24f0ff9a6204578a5c876db53142edd9d2cd56/predictions/{}";

        url = StrUtil.format(url, uuid);

        Request request = new Request.Builder()
                .url(url)
                .header("authority", "replicate.com")
                .header("accept", "*/*")
                .header("accept-language", "zh-CN,zh;q=0.9")
                .header("cache-control", "no-cache")
                .header("cookie", "replicate_anonymous_id=e8f64c6d-78b1-4a9b-ade1-3877d6f4e5d2; rl_page_init_referrer=RudderEncrypt%3AU2FsdGVkX1%2FQyskvs3j%2BUGnoT%2FRZExqrSBRiXwU2pr%2B%2BxlMZzVBBQxYM3Mm8YReQ; rl_page_init_referring_domain=RudderEncrypt%3AU2FsdGVkX19L1jHtLKjuFVel0oYNLrOaVFMgCG9ZuvXYFeYYiVzZubFpH0QPFudl; csrftoken=KVWARlPNQ3td4HX37JwWpG6jPyhkEDxN; sessionid=ecqvz8tv3lbdt4dki8h6w3z83hfk3oe8; rl_group_id=RudderEncrypt%3AU2FsdGVkX1%2FUVH9PkNwLHjeWkXKsvvn9QSTHqc63iaM%3D; rl_trait=RudderEncrypt%3AU2FsdGVkX19dei5ZZYDL3QrCXMv9ey2HPbw6OJ3oBlo%3D; rl_group_trait=RudderEncrypt%3AU2FsdGVkX19NsHnpsx2BqT1xClNXlm4oTeoWghVTz0M%3D; rl_anonymous_id=RudderEncrypt%3AU2FsdGVkX1%2FQ2nLHNtrSqqdElBIxGoAkPZVPaPQaT4H257SFDyWDdHu62mGjMcbYE4%2Fy95OuhVAvPBaS54M%2B8Q%3D%3D; rl_user_id=RudderEncrypt%3AU2FsdGVkX19D2rhxLL1QLZxLyX6fK8%2B6T1wlzJlL6AU%3D; rl_session=RudderEncrypt%3AU2FsdGVkX1%2FOHCGTW%2F4%2FoniQSSBtbZTESGOYXxsPympuY5MxhlH%2BbfEDYQW32b1RHmqymnChNNUyxydZjFQCpJl62rscPZkCKoz9xTvdphdoNL843%2BaYoym3wMxC9noFTeFI7%2Bn7UIXSvhdDJkUTaQ%3D%3D")
                .header("pragma", "no-cache")
                .header("referer", "https://replicate.com/sczhou/codeformer")
                .header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            String outPutImg = "";
            Map<String, Object> map = JSON.parseObject(response.body().string());

            Map<String, Object> prediction = JSON.parseObject(JSON.toJSONString(map.get("prediction")));

            outPutImg = (String)prediction.get("output");

            if(StringUtils.isEmpty(outPutImg)){
                return this.getOutputUrl(uuid);
            } else {
                outPutUrl = outPutImg;
                System.out.println(outPutImg);
            }
            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return outPutUrl;
    }

    private String generatePicture(String imgUrl) {

        String uuid = "";

        String jsonStr = "{\"inputs\":{\"upscale\":1,\"face_upsample\":true,\"background_enhance\":true,\"codeformer_fidelity\":0.7,\"image\":\"{}\"}}";

        jsonStr = StrUtil.format(jsonStr, imgUrl);


        // 设置请求体为JSON
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);

        Request request = new Request.Builder()
                .url("https://replicate.com/api/models/sczhou/codeformer/versions/7de2ea26c616d5bf2245ad0d5e24f0ff9a6204578a5c876db53142edd9d2cd56/predictions")
                .post(requestBody)
                .header("authority", "replicate.com")
                .header("accept", "application/json")
                .header("accept-language", "zh-CN,zh;q=0.9")
                .header("cache-control", "no-cache")
                .header("content-type", "application/json")
                .header("cookie", "replicate_anonymous_id=e8f64c6d-78b1-4a9b-ade1-3877d6f4e5d2; rl_page_init_referrer=RudderEncrypt%3AU2FsdGVkX1%2FQyskvs3j%2BUGnoT%2FRZExqrSBRiXwU2pr%2B%2BxlMZzVBBQxYM3Mm8YReQ; rl_page_init_referring_domain=RudderEncrypt%3AU2FsdGVkX19L1jHtLKjuFVel0oYNLrOaVFMgCG9ZuvXYFeYYiVzZubFpH0QPFudl; csrftoken=KVWARlPNQ3td4HX37JwWpG6jPyhkEDxN; sessionid=ecqvz8tv3lbdt4dki8h6w3z83hfk3oe8; rl_group_id=RudderEncrypt%3AU2FsdGVkX1%2FUVH9PkNwLHjeWkXKsvvn9QSTHqc63iaM%3D; rl_trait=RudderEncrypt%3AU2FsdGVkX19dei5ZZYDL3QrCXMv9ey2HPbw6OJ3oBlo%3D; rl_group_trait=RudderEncrypt%3AU2FsdGVkX19NsHnpsx2BqT1xClNXlm4oTeoWghVTz0M%3D; rl_anonymous_id=RudderEncrypt%3AU2FsdGVkX1%2FQ2nLHNtrSqqdElBIxGoAkPZVPaPQaT4H257SFDyWDdHu62mGjMcbYE4%2Fy95OuhVAvPBaS54M%2B8Q%3D%3D; rl_user_id=RudderEncrypt%3AU2FsdGVkX19D2rhxLL1QLZxLyX6fK8%2B6T1wlzJlL6AU%3D; rl_session=RudderEncrypt%3AU2FsdGVkX1%2FOHCGTW%2F4%2FoniQSSBtbZTESGOYXxsPympuY5MxhlH%2BbfEDYQW32b1RHmqymnChNNUyxydZjFQCpJl62rscPZkCKoz9xTvdphdoNL843%2BaYoym3wMxC9noFTeFI7%2Bn7UIXSvhdDJkUTaQ%3D%3D")
                .header("origin", "https://replicate.com")
                .header("pragma", "no-cache")
                .header("referer", "https://replicate.com/sczhou/codeformer")
                .header("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("sec-fetch-dest", "empty")
                .header("sec-fetch-mode", "cors")
                .header("sec-fetch-site", "same-origin")
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
                .header("x-csrftoken", "KVWARlPNQ3td4HX37JwWpG6jPyhkEDxN")
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
                Map<String, Object> map = JSON.parseObject(responseBody);
                uuid =  (String) map.get("uuid");
                System.out.println(uuid);
            } else {
                System.out.println("Request failed with code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return uuid;
    }
}
