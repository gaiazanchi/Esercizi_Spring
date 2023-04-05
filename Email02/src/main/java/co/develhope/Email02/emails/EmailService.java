package co.develhope.Email02.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    @Autowired
    private final JavaMailSender javaMailSender;

    @Async
    public void sendTo(String email, String title, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom("gaia.zanchi2001@gmail.com");
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);

        String htmlContent = "<h1>Buongiorno!</h1>" +
                "<h2> Come va? </h2>" +
                "<h3>" + text + "</h3>" +
                "<img src=\"https://dalmiocuore.it/image/uploads/che-la-vita-vi-sorrida.jpg\" width=\"500\" height=\"600\">";
        message.setContent(htmlContent, "text/html; charset=utf-8");
        javaMailSender.send(message);
    }
}
