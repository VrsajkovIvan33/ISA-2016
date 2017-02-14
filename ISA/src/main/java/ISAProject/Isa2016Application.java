package ISAProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Isa2016Application {

	public static void main(String[] args) {
		SpringApplication.run(Isa2016Application.class, args);
	}
}
