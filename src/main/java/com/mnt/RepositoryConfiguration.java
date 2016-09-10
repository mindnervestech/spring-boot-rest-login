package com.mnt;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.dev.backend.models"})
//@EnableJpaRepositories(basePackages = {"com.dev.backend.dao"})
@EnableTransactionManagement
public class RepositoryConfiguration {

}
