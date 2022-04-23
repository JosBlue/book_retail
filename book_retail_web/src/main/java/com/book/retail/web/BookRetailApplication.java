package com.book.retail.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @email liu_fu_wei@163.com
 * @author: fuwei.iu
 * @date: 2022/4/21 下午2:25
 * @description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.book.retail.dao")
@ComponentScan(basePackages = {"com.book.retail"})
@EnableAsync
@EnableScheduling
public class BookRetailApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BookRetailApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BookRetailApplication.class, args);
        System.out.println("启动完成...");
    }
}
