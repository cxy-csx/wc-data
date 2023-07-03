package vip.csx.cxy.message.req;

import lombok.Data;

@Data
public class ReqWxAuth {
    private String encryptedData;
    private String iv;
    private String sessionId;
}
