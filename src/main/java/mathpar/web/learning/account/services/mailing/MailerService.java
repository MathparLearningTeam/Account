package mathpar.web.learning.account.services.mailing;

public interface MailerService {
    void sendSimpleMessage(String recipient, String subject, String content);
}
