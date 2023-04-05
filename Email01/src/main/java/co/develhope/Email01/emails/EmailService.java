package co.develhope.Email01.emails;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    @Async
    public void sendTo(String email, String title, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(text);
        simpleMailMessage.setFrom("gaia.zanchi2001@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }
}
