package com.leyou;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.leyou.user.mapper")
public class LeyouUser {
    public static void main(String[] args) {
        SpringApplication.run( LeyouUser.class,args );
    }
}
