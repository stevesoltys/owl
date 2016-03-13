package com.stevesoltys.owl;

import com.stevesoltys.owl.config.OwlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Steve Soltys
 */
@SpringBootApplication
public class Owl implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Owl.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
