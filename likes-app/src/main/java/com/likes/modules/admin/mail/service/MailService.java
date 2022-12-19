package com.likes.modules.admin.mail.service;

public interface MailService {

    boolean sendSimpleMail(String mailFrom, String mailFromNick, String mailTo, String cc, String subject, String content);

}
