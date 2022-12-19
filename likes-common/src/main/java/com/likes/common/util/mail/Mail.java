package com.likes.common.util.mail;

import java.util.List;
import java.util.Properties;

/**
 * 辅助实体类
 *
 * @author abu
 */
public class Mail {
    private String to_email;// 收件者邮箱
    private String subject;// 邮件主题
    private String content;// 邮件内容
    private String mail_smtp_host;// 发件服务器host
    private String mail_smtp_port;// 发件服务器port
    private String username;// 发件服务器用户名
    private String password;// 发件服务器密码

    private List<String> to_emails;// 群发收件人
    private List<String> files; // 附件（地址list）

    @SuppressWarnings("unused")
    private Properties properties;

    public Mail() {
        super();
    }

    public Mail(String mail_smtp_host, String mail_smtp_port, String username, String password) {
        super();
        this.mail_smtp_host = mail_smtp_host;
        this.mail_smtp_port = mail_smtp_port;
        this.username = username;
        this.password = password;
    }

    public Mail(Mail mail) {
        super();
        this.mail_smtp_host = mail.getMail_smtp_host();
        this.mail_smtp_port = mail.getMail_smtp_port();
        this.username = mail.getUsername();
        this.password = mail.getPassword();
    }

    public Mail(String to_email, String subject, String content, String mail_smtp_host, String mail_smtp_port, String username, String password, List<String> to_emails,
                List<String> files) {
        super();
        this.to_email = to_email;
        this.subject = subject;
        this.content = content;
        this.mail_smtp_host = mail_smtp_host;
        this.mail_smtp_port = mail_smtp_port;
        this.username = username;
        this.password = password;
        this.to_emails = to_emails;
        this.files = files;
    }

    public Mail(String to_email, String subject, String content, String mail_smtp_host, String mail_smtp_port, String username, String password, List<String> files) {
        super();
        this.to_email = to_email;
        this.subject = subject;
        this.content = content;
        this.mail_smtp_host = mail_smtp_host;
        this.mail_smtp_port = mail_smtp_port;
        this.username = username;
        this.password = password;
        this.files = files;
    }

    public Mail(String to_email, String subject, String content, String mail_smtp_host, String mail_smtp_port, String username, String password) {
        super();
        this.to_email = to_email;
        this.subject = subject;
        this.content = content;
        this.mail_smtp_host = mail_smtp_host;
        this.mail_smtp_port = mail_smtp_port;
        this.username = username;
        this.password = password;
    }

    public String getTo_email() {
        return to_email;
    }

    public void setTo_email(String to_email) {
        this.to_email = to_email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMail_smtp_host() {
        return mail_smtp_host;
    }

    public void setMail_smtp_host(String mail_smtp_host) {
        this.mail_smtp_host = mail_smtp_host;
    }

    public String getMail_smtp_port() {
        return mail_smtp_port;
    }

    public void setMail_smtp_port(String mail_smtp_port) {
        this.mail_smtp_port = mail_smtp_port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", this.mail_smtp_host);
        properties.put("mail.smtp.port", this.mail_smtp_port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("username", this.username);
        properties.put("password", this.password);
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getTo_emails() {
        return to_emails;
    }

    public void setTo_emails(List<String> to_emails) {
        this.to_emails = to_emails;
    }

}
