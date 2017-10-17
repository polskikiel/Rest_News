package com.newssheet.restthebest;

import com.newssheet.restthebest.services.AsyncServices;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class RestthebestApplication implements CommandLineRunner{
	AsyncServices asyncServices;

	public static void main(String[] args) {
		SpringApplication.run(RestthebestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			asyncServices.getArticles();
			try {
				Thread.sleep(1000000);	//~15min
			} catch (InterruptedException ie) {
				return;
			}
		}
	}
}
