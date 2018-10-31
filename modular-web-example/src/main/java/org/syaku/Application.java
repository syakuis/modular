package org.syaku;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 31/10/2018
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.syaku")
@EnableJpaRepositories("org.syaku")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
