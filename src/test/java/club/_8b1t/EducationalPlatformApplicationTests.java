package club._8b1t;

import club._8b1t.utils.PasswordEncoderUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducationalPlatformApplicationTests {

	private static final String SALT = "educational-platform";

	@Test
	void contextLoads() {
	}

	@Test
	void createPassword(){
		String password = "123456";
		password += SALT;
		System.out.println(PasswordEncoderUtils.encodePassword(password));
	}

}
