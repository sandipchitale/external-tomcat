package com.opentext.ia.externaltomcat;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ExternalTomcatApplication extends SpringBootServletInitializer {

	@RestController
	public static class IndexController {

		@GetMapping("/")
		public String index(HttpServletRequest request) {
			return request.getServletContext().getServerInfo();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ExternalTomcatApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ExternalTomcatApplication.class);
	}
}
