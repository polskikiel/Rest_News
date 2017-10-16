package com.newssheet.restthebest;

import com.newssheet.restthebest.services.AsyncServices;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@AllArgsConstructor
public class RestthebestApplication implements CommandLineRunner{
	AsyncServices asyncServices;

	public static void main(String[] args) {
		SpringApplication.run(RestthebestApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			asyncServices.getArticles();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
}
