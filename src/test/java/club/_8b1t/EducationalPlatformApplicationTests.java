package club._8b1t;

import club._8b1t.utils.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EducationalPlatformApplicationTests {

    @Test
	void contextLoads() {
	}

	@Test
	void createPassword(){
		String password = "a12345678";
		System.out.println(PasswordEncoderUtil.encodePassword(password));
	}

}
