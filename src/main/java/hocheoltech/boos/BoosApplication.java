package hocheoltech.boos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("hocheoltech.boos.oauth.properties")
public class BoosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoosApplication.class, args);
	}

}
