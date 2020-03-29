package mathpar.web.frontend.application;

import mathpar.web.learning.account._configs._init.LearningApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = LearningApplication.class)
class LearningApplicationTests {
	@Test
	void contextLoads() {
	}

}
