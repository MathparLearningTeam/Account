package mathpar.web.learning.account.services.mailing;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class DummyMailService implements MailerService{
    @Override
    public void sendSimpleMessage(String recipient, String subject, String content) {
        System.out.println(String.format("Was going to send message %s  with content %s to email %s, but running not in production profile, thus skipping", subject, content, recipient));
    }
}
