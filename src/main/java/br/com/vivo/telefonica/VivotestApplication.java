package br.com.vivo.telefonica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.vivo.telefonica.util.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class VivotestApplication {

	public static void main(String[] args) {
		SpringApplication.run(VivotestApplication.class, args);
	}

}
