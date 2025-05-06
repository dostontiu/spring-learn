package agg.selm.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ManagerApplication {
    public static void main(String[] args) {
        System.out.println(new Date());
        SpringApplication.run(ManagerApplication.class, args);
    }
}
