package com.liulu.wechat;

import com.liulu.utils.SHA1;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 刘璐 on 2018/7/23.
 */
@RestController
public class auditWechatController {

    private static final String weChatToken = "liulu";

    @RequestMapping(value = "wechatAudit")
    public String wechatAudit(String signature, String timestamp,String nonce,String echostr){

        List<String> list = new ArrayList<>();
        list.add(timestamp);
        list.add(nonce);
        list.add(weChatToken);
        Collections.sort(list);
        StringBuffer sb = new StringBuffer();
        for (String str : list){
            sb.append(str);
        }
        String encrypt = SHA1.encode(sb.toString());
        if (encrypt.equals(signature)){
            return echostr;
        }
        return "失败";
    }
}
