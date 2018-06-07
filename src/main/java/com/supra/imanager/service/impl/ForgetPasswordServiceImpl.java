/*package com.supra.imanager.service.impl;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.supra.imanager.bean.Response;
import com.supra.imanager.repository.ForgetPasswordRepository;
import com.supra.imanager.service.ForgetPasswordService;

@Service
public class ForgetPasswordServiceImpl implements ForgetPasswordService {

	@Autowired
	ForgetPasswordRepository forgotPasswordRepository;

	@Value("${protocol}")
	private String protocol;

	@Value("${timesheetcontextpath}")
	private String timesheetcontextpath;

	@Value("${domain}")
	private String domain;

	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;

	@Value("${TEST_DELIVERY_MAIL_TO2}")
	private String TEST_DELIVERY_MAIL_TO2;
	
	

	//// Forget paassword mail 2////
	@Override
	public boolean checkUserExist(String usermail) {
		String userExist = forgotPasswordRepository.checkUserExist(usermail);
		boolean status = false;
		if (null != userExist) {
			status = true;
			return status;
		}
		return status;
	}
	
	
	
	
	

	//// Forget paassword mail 3////
	@Override
	public String saveActivationLink(String email) {
		boolean status = false;

		String temp = createActivationRamdomKey();
		// token getting saved in database
		String savedStatus = forgotPasswordRepository.saveActivationLinkInDb(email, temp);

		if (savedStatus != null) {
			// link preparation

			String activationLink = protocol + "://" + domain + "/" + timesheetcontextpath + "/setNewPasswordlogin?token="
					+ temp;
			Map<String, Object> mailContentMap = new HashMap<String, Object>();
			mailContentMap.put("usermail", email);
			mailContentMap.put("link", activationLink);
			final String toMailId = email;
			final Map<String, Object> mailContent = mailContentMap;
			Thread t = new Thread() {
				@Override
				public void run() {
					doSendTemplateEmail2(toMailId, "eManager Password reset link.", mailContent,
							"passwordResetLinkVM.vm");
				}
			};

			t.start();
			status = true;
		}
		return savedStatus;
	}

////Forget paassword mail 6////
	private String createActivationRamdomKey() {
		// TODO Auto-generated method stub
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(11);
		for (int i = 0; i < 11; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		String temp = new String(sb);
		return temp;
	}

	//// Forget paassword mail 5////

	public String doSendTemplateEmail2(String recipientAddress, String subject, Map<String, Object> model,
			String templateName) {
		// SimpleMailMessage email = new SimpleMailMessage();
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
		try {
			email.setFrom(ADMIN_MAIL);
			if (domain.equalsIgnoreCase("timesheet.supraes.com"))
				email.setTo(recipientAddress);
			else
				email.setTo(TEST_DELIVERY_MAIL_TO2);
			email.setSubject(subject);
			String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
			email.setText(message, true);
			this.mailSender.send(mimeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Result";
	}

	//// Forget paassword mail 7////

	@Override
	public void prepareMailSentResponse(String activationLinkSaved, Response mailResponse, String usermail) {
		if (activationLinkSaved != null) {
			mailResponse.setResponseCode(HttpStatus.OK.value());
			mailResponse.setResponseMessage("Email Sent Successfully to " + usermail + ". Please checck mailbox.");
			mailResponse.setResponseData(null);
		} else {

			mailResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
			mailResponse.setResponseMessage("Email Not Sent Successfully.");
			mailResponse.setResponseData(null);

		}
	}

}
*/