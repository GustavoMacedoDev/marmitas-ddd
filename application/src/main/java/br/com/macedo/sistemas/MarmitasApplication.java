package br.com.macedo.sistemas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarmitasApplication {

	 private static final Logger logger = LoggerFactory.getLogger(MarmitasApplication.class);
	     public static void main(String[] args) {
	         SpringApplication.run(MarmitasApplication.class, args);
	         logger.info("MarmitasApplication Started........");
	     }

}
