package SpringBootAPI.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.course")
public class application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(application.class, args);
    }
}
