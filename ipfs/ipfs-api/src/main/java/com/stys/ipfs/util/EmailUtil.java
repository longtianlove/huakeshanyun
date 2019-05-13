package com.stys.ipfs.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 信邮发送工具帮助
 * 
 *
 */
public class EmailUtil {

	protected static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	public final static String hostName = "smtp.sohu.com";// 协议
	public final static String from = "huakeshanyun@sohu.com";// 发件人
	public final static String userName = "huakeshanyun";// 登陆名
	public final static String password = "root@123";// smt协议 密码  网页 ruidev123456!
	public final static int smtpPort = 25;//
	public final static String sslSmtpPort = "465";//

	public static Email getDefaultEmailConfig(Email email) throws EmailException {
		// Email email = new SimpleEmail();
		email.setHostName(hostName);
		email.setSslSmtpPort(sslSmtpPort);
		email.setAuthenticator(new DefaultAuthenticator(userName, password));// @163.com
		email.setSSLOnConnect(true);
		email.setFrom(from);
		email.setCharset("utf-8");

		return email;
	}

	/**
	 * 发送邮件 bean组装
	 * <p>
	 * 如果有ftl名字 and EmailAttachment 就先发这个,优先
	 * 
	 * @param email
	 *            bean 邮件内容封装bean
	 * @throws EmailException
	 *//*
	public static void sendEmailToBean(EmailBean email) throws EmailException {

		// 如果有flt文件名传来
		if (email == null) {
			throw new EmailException("对象不能为空");
		}

		if (!StringUtils.isBlank(email.getFtlName())) {
			if (email.getEmailAttachment() != null) {// 发送ftl模板邮件 +附件
				sendMultiPartEmailAndFtl(email.getMap(), email.getFtlName(), email.getSubject(), email.getToEmail(),
						email.getToEmailName(), email.getEmailAttachment());
				return;
			}
			// 发送ftl模板邮件
			sendFtlEmail(email.getMap(), email.getFtlName(), email.getSubject(), email.getToEmail(),
					email.getToEmailName());
			return;
		}

		// 发送普通带符件的邮件
		if (email.getEmailAttachment() != null) {
			sendMultiPartEmail(email.getContext(), email.getSubject(), email.getToEmail(), email.getToEmailName(),
					email.getEmailAttachment());
			return;
		}

		// 普通文本邮件
		sendContextEmail(email.getContext(), email.getSubject(), email.getToEmail(), email.getToEmailName());

	}
*/
	/**
	 * 发送简单文本邮件
	 * 
	 * @param context邮件文本
	 * @param subject
	 *            主题
	 * @param toEmail
	 *            发送给谁的邮件地址
	 * @param toEmailName
	 *            名称
	 * @throws EmailException
	 */
	public static void sendContextEmail(String context, String subject, String toEmail, String toEmailName)
			throws EmailException {
		if (StringUtils.isBlank(context)) {
			throw new EmailException("邮件文本不能为空");
		}
		if (StringUtils.isBlank(toEmail)) {
			throw new EmailException("邮件接收人不能为空");
		}
		if (StringUtils.isBlank(subject)) {
			throw new EmailException("邮件主题不能为空");
		}

		try {
			Email email = getDefaultEmailConfig(new SimpleEmail());
			email.setSubject(subject);
			email.setMsg(context);

			if (StringUtils.isBlank(toEmailName)) {
				// 接收人
				email.addTo(toEmail);
			} else {
				// 接收人
				email.addTo(toEmail, toEmailName);// 1067165280@qq.com
													// rui_dev@126.com
			}

			email.send();
		} catch (EmailException e) {
			logger.error("邮件发送异常!{}" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 发送简单文本邮件
	 * 
	 * @param context邮件文本
	 * @param subject
	 *            主题
	 * @param toEmail
	 *            发送给谁的邮件地址
	 * @param toEmailName
	 *            名称
	 * @throws EmailException
	 */
	public static void sendContextEmailHTML(String context, String subject, String toEmail, String toEmailName)
			throws EmailException {
		if (StringUtils.isBlank(context)) {
			throw new EmailException("邮件文本不能为空");
		}
		if (StringUtils.isBlank(toEmail)) {
			throw new EmailException("邮件接收人不能为空");
		}
		if (StringUtils.isBlank(subject)) {
			throw new EmailException("邮件主题不能为空");
		}
		try {
			HtmlEmail email = (HtmlEmail) getDefaultEmailConfig(new HtmlEmail());
			email.setSubject(subject);
			email.setHtmlMsg(context);
			if (StringUtils.isBlank(toEmailName)) {
				// 接收人
				email.addTo(toEmail);
			} else {
				// 接收人
				email.addTo(toEmail, toEmailName); 
			}

			email.send();
		} catch (EmailException e) {
			logger.error("邮件发送异常!{}" + e.getMessage());
			e.printStackTrace();
		}

	}

	/**
	 * 发送带符件邮件的简单文本邮件
	 * 
	 * @param context
	 *            邮件文本
	 * @param subject
	 *            主题
	 * @param toEmail
	 *            发送给谁的邮件地址
	 * @param toEmailName
	 *            名称
	 * @throws EmailException
	 */
	public static void sendMultiPartEmail(String context, String subject, String toEmail, String toEmailName,
			EmailAttachment emailAttachment) throws EmailException {
		if (StringUtils.isBlank(context)) {
			throw new EmailException("邮件文本不能为空");
		}
		if (StringUtils.isBlank(toEmail)) {
			throw new EmailException("邮件接收人不能为空");
		}
		if (StringUtils.isBlank(subject)) {
			throw new EmailException("邮件主题不能为空");
		}
		if (emailAttachment == null) {
			throw new EmailException("附件不能为空");
		}

		try {
			MultiPartEmail email = (MultiPartEmail) getDefaultEmailConfig(new MultiPartEmail());
			email.setSubject(subject);
			email.setMsg(context);
			if (StringUtils.isBlank(toEmailName)) {
				// 接收人
				email.addTo(toEmail);
			} else {
				// 接收人
				email.addTo(toEmail, toEmailName);// 1067165280@qq.com
													// rui_dev@126.com
			}
			email.attach(emailAttachment);
			email.send();
		} catch (EmailException e) {
			logger.error("邮件发送异常!{}" + e.getMessage());
			e.printStackTrace();
		}

	}

	 
	
	public static void send(String subject,String content,String toemail) throws Exception{
		Email email = new SimpleEmail();
		email.setHostName(hostName);
		email.setSslSmtpPort(sslSmtpPort);
		email.setAuthenticator(new DefaultAuthenticator(userName, password));// @163.com
		email.setSSLOnConnect(true);
		// 发送人
		email.setFrom(from);// 设置字段的电子邮件使用指定的地址。电子邮件
		email.setSubject(subject);

		email.setMsg(content);
		// 接收人
		email.addTo(toemail, "toName"); 
													 
		String result=email.send();

		System.out.println(toemail+"---emailok>>>"+content); 
	}
 
	public static void main(String[] args) throws EmailException {
		
 	try {
			send("【华科闪云】","您购买的ipfs8T存储已经到账","357561324@qq.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		/* Email email = new SimpleEmail();
		email.setHostName(hostName);
		email.setSslSmtpPort(sslSmtpPort);
		email.setAuthenticator(new DefaultAuthenticator(userName, password));// @163.com
		email.setSSLOnConnect(true);
		// 发送人
		email.setFrom(from);// 设置字段的电子邮件使用指定的地址。电子邮件
		email.setSubject("送行-梁实秋");

		email.setMsg("发邮件simple test");
		// 接收人
		email.addTo("1271876110@qq.com", "toName"); 
													 
		email.send();*/
 
		System.out.println("ok>>>");
	}

}
