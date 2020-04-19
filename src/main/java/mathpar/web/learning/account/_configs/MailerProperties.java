package mathpar.web.learning.account._configs;

import mathpar.web.learning.account.utils.MathparProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailerProperties {
    @Bean
    @Profile("!test")
    public JavaMailSender mailSender(MathparProperties properties){
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getMailHost());
        mailSender.setPort(properties.getMailPort());
        mailSender.setUsername(properties.getMailUsername());
        mailSender.setPassword(properties.getMailPassword());
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
