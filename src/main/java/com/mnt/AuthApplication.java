package com.mnt;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableAutoConfiguration
//@EnableScheduling
@SpringBootApplication
//@ComponentScan
public class AuthApplication  {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
		System.out.println("here");
	}
	
	
	@Bean(name="sessionFactory")
    public SessionFactory sessionFactory() {
        return ((HibernateEntityManagerFactory) this.entityManagerFactory).getSessionFactory();
    }
}
