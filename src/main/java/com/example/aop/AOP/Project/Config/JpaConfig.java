package com.example.aop.AOP.Project.Config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
@EnableTransactionManagement
@ComponentScan(basePackages = "com.example.aop.AOP.Project")
public class JpaConfig {

}