package kc.logix.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import kainos.framework.core.lang.KainosMailException;
import kainos.framework.core.model.KainosMailDto;
import kainos.framework.core.model.KainosMailDto.FileAttachment;
import kainos.framework.core.model.KainosMailDto.ReportAttachment;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 
	 * @param mailDto
	 * @return
	 * @throws KainosMailException
	 */
	public byte[] sendMessage(KainosMailDto mailDto) throws KainosMailException {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
	        helper.setFrom(mailDto.getFrom());
	        helper.setSubject(mailDto.getSubject());
	        helper.setTo(mailDto.getTo().toArray(new InternetAddress[0]));
	        
	        if(mailDto.getCc().size() > 0)
	        	helper.setCc(mailDto.getCc().toArray(new InternetAddress[0]));
	        for (FileAttachment attachment : mailDto.getAttachment()) {
	        	if(attachment.getFile() instanceof MultipartFile file) 
	        		helper.addAttachment(file.getOriginalFilename(), file);
	        	else if(attachment.getFile() instanceof File file) 
	        		helper.addAttachment(file.getName(), file);
	        }
	        
	        for (ReportAttachment attachment : mailDto.getReports())
	        	helper.addAttachment(MimeUtility.encodeText(attachment.getFileName()), new ByteArrayResource(attachment.getFile()));
	        
	        helper.setText(mailDto.getMailbody(), mailDto.getHtml());
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        msg.writeTo(baos);
	        return baos.toByteArray(); 
        } catch (Exception e) {
            throw new KainosMailException(e);
        }
    }
	
	public void send(KainosMailDto mailDto) throws KainosMailException {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
	        helper.setFrom(mailDto.getFrom());
	        helper.setSubject(mailDto.getSubject());
	        helper.setTo(mailDto.getTo().toArray(new InternetAddress[0]));
	        
	        if(mailDto.getCc().size() > 0)
	        	helper.setCc(mailDto.getCc().toArray(new InternetAddress[0]));
	        for (FileAttachment attachment : mailDto.getAttachment()) {
	        	if(attachment.getFile() instanceof MultipartFile file) 
	        		helper.addAttachment(file.getOriginalFilename(), file);
	        	else if(attachment.getFile() instanceof File file) 
	        		helper.addAttachment(file.getName(), file);
	        }
	        
	        for (ReportAttachment attachment : mailDto.getReports())
	        	helper.addAttachment(MimeUtility.encodeText(attachment.getFileName()), new ByteArrayResource(attachment.getFile()));
	        
	        helper.setText(mailDto.getMailbody(), mailDto.getHtml());
	        mailSender.send(msg);
        } catch (Exception e) {
            throw new KainosMailException(e);
        }
    }
}
