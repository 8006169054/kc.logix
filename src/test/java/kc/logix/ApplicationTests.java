package kc.logix;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private JavaMailSender mailSender;
    
	@Test
	void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("is.jung@hmm21.com");
		message.setReplyTo("noreply@kclogix.com"); // 보내는 사람의 이메일(프론트단에서 이메일 입력으로 받음)
		message.setText("테스트 메일 입니다."); // 이메일 내용
		message.setSubject("이메일 문의");
		
		mailSender.send(message);
	}

}
