package com.sunchs.store.framework.util;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class MailUtil {

    private static String from;
    private static String fromName;
    private static String to;
    private static String toName;
    private static String subject;
    private static String content;

    public static void sendMail(Map<String, String> param){
        initParam(param);

        Session session = Session.getInstance(createProperties());
        session.setDebug(true);
        MimeMessage msg = createMessage(session);
        sendMessage(session, msg);
    }

    private static void initParam(Map<String, String> param) {
        if ( ! param.containsKey("from")) {
            throw new RuntimeException("请设置发送者邮箱");
        }
        if ( ! param.containsKey("to")) {
            throw new RuntimeException("请设置接收者址");
        }
        if ( ! param.containsKey("subject")) {
            throw new RuntimeException("请设置邮件主题");
        }
        if ( ! param.containsKey("content")) {
            throw new RuntimeException("请设置邮件内容");
        }

        from = param.get("from");
        to = param.get("to");
        if (param.containsKey("fromName")) {
            fromName = param.get("fromName");
        }
        if (param.containsKey("toName")) {
            toName = param.get("toName");
        }
        subject = param.get("subject");
        content = param.get("content");
    }

    private static void sendMessage(Session session, MimeMessage msg) {
        try {
            Transport transport = session.getTransport();
            transport.connect("king@sunchs.com", "Zqc+0612");
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Properties createProperties() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.exmail.qq.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");

        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        return props;
    }

    private static MimeMessage createMessage(Session session) {
        try {
            MimeMessage msg = new MimeMessage(session);
            if (StringUtil.isNotEmpty(fromName)) {
                msg.setFrom(new InternetAddress(from, fromName, "utf-8"));
            } else {
                msg.setFrom(new InternetAddress(from));
            }
            if (StringUtil.isNotEmpty(toName)) {
                msg.setRecipient(RecipientType.TO, new InternetAddress(to, toName, "utf-8"));
            } else {
                msg.setRecipient(RecipientType.TO, new InternetAddress(to));
            }

            msg.setSubject(subject, "utf-8");
            msg.setContent(content, "text/html;charset=utf-8");

            // 选项
            msg.setSentDate(new Date());
            msg.saveChanges();
            return msg;
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
