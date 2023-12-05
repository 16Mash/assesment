package technical_assesment.assesment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "technical_assessment.assesment.bean")
public class AssesmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssesmentApplication.class, args);
	}

}
