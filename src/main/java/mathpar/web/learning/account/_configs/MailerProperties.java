package mathpar.web.learning.account._configs;

import mathpar.web.learning.account.utils.MathparProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailerProperties {
    @Bean
    @Profile("!test")
    public JavaMailSender mailSender(MathparProperties properties){
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getMailHost());
        mailSender.setPort(properties.getMailPort());
        mailSender.setUsername(properties.getMailUsername());
        mailSender.setPassword(properties.getMailPassword());
        return mailSender;
    }
}
