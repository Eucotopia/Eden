package top.easylove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EdenApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdenApplication.class, args);
	}

}
