package com.likes.modules.admin.mail.service.impl;

import com.alibaba.fastjson.JSON;
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.likes.common.constant.RedisKeys;
import com.likes.common.enums.MsgTypeEnum;
import com.likes.common.model.dto.SendEmailDto;
import com.likes.common.mybatis.entity.SysShortmsg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Component
public class EmailService {

    @Value("${aws.email.address}")
    private String emailAddress;// 收件人地址

    @Value("${aws.email.regions.name}")
    private String regionName = "us-east-1";//

    @Value("${aws.email.template.register.subject}")
    private String registerSubject;  // 标题

    @Value("${aws.email.template.register.body}")
    private String registerBody;  // neirong
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 发送注册邮件
     *
     * @param to
     * @param code
     */
    public void sendRegisterEmail(String to, String code) {
        String body = "Your verification code is  " + code;
        sendEmail(emailAddress, to, registerSubject, body);
        SysShortmsg sysShortmsg = new SysShortmsg();
        sysShortmsg.setMsgcode(code);
        redisTemplate.opsForHash().put(RedisKeys.LIVE_SMS_CODE_DETAIL, to + RedisKeys.LIVE_JOINT_COLON + MsgTypeEnum.REGISTER.getValue(), sysShortmsg);

    }

    public void sendEmail(String from, String to, String subject, String body) {
        SendEmailDto dto = new SendEmailDto();
        dto.setFromEmailAddress(from);
        dto.setToEmailAddress(to);
        dto.setSubject(subject);
        dto.setBody(body);
        send(dto);
    }

    private void send(SendEmailDto dto) {
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(dto.getToEmailAddress());

        // Create the subject and body of the message.
        Content subject = new Content().withData(dto.getSubject());
        Content textBody = new Content().withData(dto.getBody());
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(dto.getFromEmailAddress()).withDestination(destination).withMessage(message);

        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            /*
             * The ProfileCredentialsProvider will return your [default]
             * credential profile by reading from the credentials file located at
             * (~/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
            AWSCredentials credentials = null;
            try {
                credentials = new ProfileCredentialsProvider().getCredentials();
            } catch (Exception e) {
                throw new AmazonClientException("Cannot load the credentials from the credential profiles file. " + "Please make sure that your credentials file is at the correct " + "location (~/.aws/credentials), and is in valid format.", e);
            }

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
            // access status, sending limits, and Amazon SES identity-related settings are specific to a given
            // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US West (Oregon) region. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.fromName(regionName)).build();

            // Send the email.
            client.sendEmail(request);
            System.out.println("Email sent!");

        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }

}
