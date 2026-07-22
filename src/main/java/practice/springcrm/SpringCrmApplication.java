package practice.springcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "practice.springcrm")
public class SpringCrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCrmApplication.class, args);
    }

}
