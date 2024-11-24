package club._8b1t;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@MapperScan("club._8b1t.mapper")
public class EducationalPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationalPlatformApplication.class, args);
	}

}
