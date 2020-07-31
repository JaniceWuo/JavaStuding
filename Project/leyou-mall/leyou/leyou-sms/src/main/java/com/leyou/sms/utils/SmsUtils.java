package com.leyou.sms.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.lang3.StringUtils;
import com.leyou.sms.config.SmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsUtils {

    @Autowired
    private SmsProperties prop;
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
//    static final Logger logger = LoggerFactory.getLogger( SmsUtils.class );

    public void sendSms(String phone, String templateCode, String signName, String verifyCode ) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", prop.getAccessKeyId(), prop.getAccessKeySecret());
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GK7jUDrLmWNCdoFK2pd", "D2ZC77xF62s29jeMW1k6aklw9CAbKr");
        IAcsClient acsClient = new DefaultAcsClient( profile );
        CommonRequest request = new CommonRequest();

        request.setMethod( MethodType.POST );
        request.setDomain( "dysmsapi.aliyuncs.com" );
        request.setVersion( "2017-05-25" );
        request.setAction( "SendSms" );
        request.putQueryParameter( "RegionId", "cn-hangzhou" );
        request.putQueryParameter( "PhoneNumbers", phone);
        request.putQueryParameter( "SignName", signName);
        request.putQueryParameter( "TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifyCode + "\"}");

        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
