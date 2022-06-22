package cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.wolfcode.mapper")
public class SpringbootSsm2220408Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsm2220408Application.class, args);
    }

}
