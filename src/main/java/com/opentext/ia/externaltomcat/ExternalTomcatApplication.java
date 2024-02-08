package com.opentext.ia.externaltomcat;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
public class ExternalTomcatApplication extends SpringBootServletInitializer {

	@RestController
	public static class IndexController {

		private String env;
        public IndexController(Environment environment) {
			Map<String, Object> map = new HashMap<>();
			if (environment instanceof  ConfigurableEnvironment configurableEnvironment) {
				MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
				propertySources.stream().forEach((PropertySource<?> propertySource) -> {
					if (propertySource instanceof MapPropertySource mapPropertySource) {
						map.putAll(mapPropertySource.getSource());
					}
				});
			}
			String string = map.toString();
			string = string.substring(1, string.length() - 1);
			env = Arrays.stream(string.split(", ")).sorted().collect(Collectors.joining("<br/>"));
        }

        @GetMapping("/")
		public String index(HttpServletRequest request) {
			return env;
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
