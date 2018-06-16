package com.supra.imanager.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.supra.imanager.bean.Response;
import com.supra.imanager.dto.SupraPasswordActivation;
import com.supra.imanager.repository.RestTokenRepository;
import com.supra.imanager.repository.SupraPasswordActivationRepository;
import com.supra.imanager.repository.UserRepository;
import com.supra.imanager.service.UserService;
import com.supra.imanager.utilities.ApplicationUtilities;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTokenRepository restTokenRepository; 
	
	@Autowired
	private SupraPasswordActivationRepository supraPasswordActivationRepository;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Value("${timesheetcontextpath}")
	private String timesheetcontextpath;

	@Value("${app.domainUrl}")
	private String domain;

	@Value("${ADMIN_MAIL}")
	private String ADMIN_MAIL;

	@Value("${TEST_DELIVERY_MAIL_TO2}")
	private String TEST_DELIVERY_MAIL_TO2;
	

	@Override
	public String saveActivationLink(String email) {
		
		String temp = ApplicationUtilities.createActivationRamdomKey();
		// token getting saved in database
		SupraPasswordActivation supraPasswordActivation = new SupraPasswordActivation(email, temp);
		SupraPasswordActivation savedStatus = supraPasswordActivationRepository.save(supraPasswordActivation);
		 String sent=null;
		if (savedStatus.getId() != null) {
			// link preparation
			
			String activationLink = domain + "/" + timesheetcontextpath + "/setNewPasswordlogin?token="
					+ temp;
			Map<String, Object> mailContentMap = new HashMap<String, Object>();
			mailContentMap.put("usermail", email);
			mailContentMap.put("link", activationLink);
			final String toMailId = email;
			final Map<String, Object> mailContent = mailContentMap;
			
				 boolean  mailSent=false ;
					mailSent = doSendTemplateEmail(toMailId, "eManager Password reset link.", mailContent,
							"templates/passwordResetLinkVM.vm");
				
					if(mailSent) {
						sent="Sent";
						restTokenRepository.deleteByUserName(email);
					}
					else {
						sent="NotSent";	
					}
		}
		return sent;
	}


	public boolean doSendTemplateEmail(String recipientAddress, String subject, Map<String, Object> model,
			String templateName) {
		boolean flag=false;
		// SimpleMailMessage email = new SimpleMailMessage();
		final MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper email = new MimeMessageHelper(mimeMessage);
		try {
			email.setFrom(ADMIN_MAIL);
			/*if (domain.equalsIgnoreCase("timesheet.supraes.com")) {
				email.setTo(recipientAddress);
			}
			else {
				email.setTo(TEST_DELIVERY_MAIL_TO2);
			}*/
			email.setTo(recipientAddress);
			email.setSubject(subject);
			String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model);
			email.setText(message, true);
			this.mailSender.send(mimeMessage);
			flag=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public Response prepareMailSentResponse(String activationLinkSaved, Response mailResponse, String usermail) {
		
		if (activationLinkSaved.equals("Sent")) {
			mailResponse.setResponseCode(HttpStatus.OK.value());
			mailResponse.setResponseMessage("Email Sent Successfully to " + usermail + ". Please check mailbox.");
			mailResponse.setResponseData(null);
			
		} 
		else {

			mailResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
			mailResponse.setResponseMessage("Email Not Sent Successfully.");
			mailResponse.setResponseData(null);
			
		}
		
		return mailResponse;
	}

}
