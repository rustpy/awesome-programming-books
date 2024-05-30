package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot启动项
 *
 * @author: yong.peng
 * @create: 2021/9/31 16:20
 */
@SpringBootApplication
@Slf4j
public class uitestToolApplication {

    public static void main(String[] args) {
        try {
            SpringApplication application = new SpringApplication(uitestToolApplication.class);
            application.setWebApplicationType(WebApplicationType.REACTIVE);
            application.run(args);
        } catch (Exception e) {
            log.error("application error", e);
        }
    }

}
