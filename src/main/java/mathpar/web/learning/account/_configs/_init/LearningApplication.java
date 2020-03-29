package mathpar.web.learning.account._configs._init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"mathpar.web.learning.account.*"})
public class LearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}
}
