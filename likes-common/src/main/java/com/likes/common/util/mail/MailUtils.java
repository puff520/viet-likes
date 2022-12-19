package com.likes.common.util.mail;

import java.io.IOException;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送
 * 
 * @author abu
 *
 */
public class MailUtils {

	/**
	 * 发送单一邮件(可多附件)
	 * 
	 * @param mail
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 */
	/*public static void send(Mail mail) throws IOException, AddressException, MessagingException {
		Properties properties = mail.getProperties();
		MailAuthenticator authenticator = new MailAuthenticator(properties.get("username").toString(), properties.get("password").toString());
		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(properties.get("username").toString()));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo_email()));
		mailMessage.setSubject(mail.getSubject(), "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 多附件
		if (mail.getFiles() != null && mail.getFiles().size() > 0)
			for (String filename : mail.getFiles()) {
				MimeBodyPart mbp = new MimeBodyPart();
				// 得到数据源
				FileDataSource fds = new FileDataSource(filename);
				// 得到附件本身并至入BodyPart
				mbp.setDataHandler(new DataHandler(fds));
				// 得到文件名同样至入BodyPart
				mbp.setFileName(fds.getName());
				mainPart.addBodyPart(mbp);
			}
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		html.setContent(mail.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		// 设置信件头的发送日期
		mailMessage.setSentDate(new Date());
		mailMessage.saveChanges();
		Transport.send(mailMessage);
	}

	*//**
	 * 群发邮件（可多附件）
	 * 
	 * @param mail
	 * @throws IOException
	 * @throws AddressException
	 * @throws MessagingException
	 *//*
	public static void batchSend(Mail mail) throws IOException, AddressException, MessagingException {
		Properties properties = mail.getProperties();
		MailAuthenticator authenticator = new MailAuthenticator(properties.get("username").toString(), properties.get("password").toString());
		javax.mail.Session sendMailSession = javax.mail.Session.getDefaultInstance(properties, authenticator);
		MimeMessage mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(new InternetAddress(properties.get("username").toString()));
		// Message.RecipientType.TO属性表示接收者的类型为TO
		InternetAddress[] address = new InternetAddress[mail.getTo_emails().size()];
		for (int i = 0; i < mail.getTo_emails().size(); i++) {
			address[i] = new InternetAddress(mail.getTo_emails().get(i));
		}
		mailMessage.setRecipients(Message.RecipientType.TO, address);
		mailMessage.setSubject(mail.getSubject(), "UTF-8");
		mailMessage.setSentDate(new Date());
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart mainPart = new MimeMultipart();
		// 多附件
		if (mail.getFiles() != null && mail.getFiles().size() > 0)
			for (String filename : mail.getFiles()) {
				MimeBodyPart mbp = new MimeBodyPart();
				// 得到数据源
				FileDataSource fds = new FileDataSource(filename);
				// 得到附件本身并至入BodyPart
				mbp.setDataHandler(new DataHandler(fds));
				// 得到文件名同样至入BodyPart
				mbp.setFileName(fds.getName());
				mainPart.addBodyPart(mbp);
			}
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart html = new MimeBodyPart();
		html.setContent(mail.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		// 设置信件头的发送日期
		mailMessage.setSentDate(new Date());
		mailMessage.saveChanges();
		Transport.send(mailMessage);
	}

	public static void main(String args[]) {
		try {
			Mail mail = new Mail();
			mail.setMail_smtp_host("smtp.exmail.qq.com");
			mail.setMail_smtp_port("465");
			mail.setUsername("");
			mail.setPassword("");
			mail.setTo_email("");
			mail.setSubject("班豆网验证码");
			mail.setContent("<b>  验证码为：</b>235453");
//			List<String> files = new ArrayList<String>();
//			files.add("E:\\班豆原型.txt");
//			files.add("E:\\QQ邮箱POP3.png");
//			files.add("E:\\QQ邮箱SMTP.png");
//			mail.setFiles(files);

			send(mail);
			List<String> to_emails = new ArrayList<String>();
			to_emails.add("504597591@qq.com");
			to_emails.add("619933703@qq.com");
			mail.setTo_emails(to_emails);
			batchSend(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }*/
}
