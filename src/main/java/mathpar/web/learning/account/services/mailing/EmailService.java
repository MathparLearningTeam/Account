package mathpar.web.learning.account.services.mailing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class EmailService implements MailerService{
    private final JavaMailSender mailSender;
    private final String from;

    public EmailService(JavaMailSender mailSender, @Value("{spring.mail.username}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }


    @Override
    public void sendSimpleMessage(String recipient, String subject, String content) {
        var message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}
