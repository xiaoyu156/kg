package iie.ac.cn.calculation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class KgCalculationApplication {

    public static void main(String[] args) {
        SpringApplication.run(KgCalculationApplication.class, args);
    }
}
