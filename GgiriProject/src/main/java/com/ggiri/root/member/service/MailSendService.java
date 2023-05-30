package com.ggiri.root.member.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSendService {
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	private int authNum;
	
	public void makeRandomNum() {
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("인증번호 : " + checkNum);
		authNum = checkNum;
	}
	
	public String joinEmail(String userEmail) {
		makeRandomNum();
		String setFrom = "ksop6580@naver.com";
		String toMail = userEmail;
		String title = "회원가입 인증 메일입니다.";
		String content = "홈페이지를 방문해주셔서 감사합니다." + "<br><br>"
				+ "인증번호는 [ " + authNum + " ] 입니다.";
		mailSend(setFrom, toMail, title, content);
		return Integer.toString(authNum);
	}
	
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper mailHelper = new MimeMessageHelper(message, true, "utf-8");
			mailHelper.setFrom(setFrom);
			mailHelper.setTo(toMail);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
