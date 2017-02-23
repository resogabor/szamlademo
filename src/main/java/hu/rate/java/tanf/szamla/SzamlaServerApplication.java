package hu.rate.java.tanf.szamla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("hu.rate.java.tanf.szamla.*")
@EntityScan("hu.rate.java.tanf.szamla.*")
public class SzamlaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SzamlaServerApplication.class, args);
	}
}
