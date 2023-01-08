package com.telkomsel.kezbek.consumer.service.impl;

import java.math.BigDecimal;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.telkomsel.kezbek.consumer.model.Transaction;
import com.telkomsel.kezbek.consumer.service.EmailSenderService;

import io.rocketbase.mail.EmailTemplateBuilder.EmailTemplateConfigBuilder;
import io.rocketbase.mail.config.TbConfiguration;
import io.rocketbase.mail.model.HtmlTextEmail;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
	
	private final JavaMailSender javaMailSender;

    public EmailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
	
	@Override
	public void sendEmail(Transaction transaction) throws MessagingException {		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Your Kezbek E-Receipt");
        helper.setText(generateEmail(transaction), true);
        helper.setTo(transaction.getEmail());
        javaMailSender.send(mimeMessage);
	}

	private String generateEmail(Transaction transaction) {
		EmailTemplateConfigBuilder builder = new EmailTemplateConfigBuilder();
		TbConfiguration config = TbConfiguration.newInstance();
		config.getContent().setFull(true);

		HtmlTextEmail htmlTextEmail = builder
		        .configuration(config)
		        .header().text("Kezbek E-Receipt").and()
		        .text("Hi " + transaction.getEmail() + ",").and()
		        .text("Thanks for using Kezbek to purchase you product from our partner. This is a receipt for your recent purchase").and()
		        .tableSimple("#.## 'Rp'")
		        .headerRow("Description", "Amount")
		        .itemRow("Total Purchase", BigDecimal.valueOf(transaction.getPrice()))
		        .footerRow("Total Cashback", BigDecimal.valueOf(transaction.getQuantityCashback() + transaction.getTierCashback()))
		        .and()
		        .button("Download Receipt", "#").gray().right().and()
		        .text("If you have any questions about this receipt, simply reply to this email or reach out to our support team for help.").and()
		        .copyright("Kezbek Application").url("https://www.kezbek.com").suffix(". All rights reserved.").and()
		        .footerText("PT. Solusi Prima\n" +
		                "Jl. Ngalor Ngidul No.123\n" +
		                "Jakarta 32211").and()
		        .build();
		
		return htmlTextEmail.getHtml();
	}
}
