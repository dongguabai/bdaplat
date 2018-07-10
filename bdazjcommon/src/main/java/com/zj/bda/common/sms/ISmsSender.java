package com.zj.bda.common.sms;

/**
 * 短信发送
 * 1.验证码短信发送如想覆盖：smsCaptchaSender
 * @author Dongguabai
 * @date 2018-07-10 10:51
 */
public interface ISmsSender {

    /**
     * 发送短信
     * @param phoneNumber   手机号码
     * @param content       内容
     */
    void sendMessage(String phoneNumber, String content);
}
