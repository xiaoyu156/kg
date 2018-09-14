package iie.ac.cn.kgserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class KgServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgServerApplication.class, args);
    }
}
