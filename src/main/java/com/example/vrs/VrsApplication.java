package com.example.vrs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VrsApplication {

	private static Logger LOG = LoggerFactory
			.getLogger(VrsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VrsApplication.class, args);
		LOG.info("APPLICATION IS RUNNING");
	}
}
