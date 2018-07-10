package com.zj.bda.dgbsecurity.captcha.sms.send;

import com.zj.bda.common.sms.ISmsSender;

/**
 * 短信验证码默认Sender
 * @author Dongguabai
 * @date 2018-07-10 10:57
 */
public class SmsCaptchaSender implements ISmsSender {

    @Override
    public void sendMessage(String phoneNumber, String content) {
        System.out.println("当前发送的手机号码为："+phoneNumber+"，内容为："+content);
    }
}
