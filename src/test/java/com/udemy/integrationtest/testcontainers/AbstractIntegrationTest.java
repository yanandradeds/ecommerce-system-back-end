package com.udemy.integrationtest.testcontainers;

import com.udemy.service.RepositoryService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.Map;
import java.util.stream.Stream;

@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
public class AbstractIntegrationTest {

    class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
             start();
             ConfigurableEnvironment enviroment = applicationContext.getEnvironment();
             MapPropertySource testcontainers = new MapPropertySource("testcontainers", createConnectionConfig());
             enviroment.getPropertySources().addFirst(testcontainers);
        }

        private Map<String, Object> createConnectionConfig() {
            return Map.of("spring.datasource.url", mysql.getJdbcUrl(),
                    "spring.datasource.username", mysql.getUsername(),
                    "spring.datasource.password", mysql.getPassword());
        }
    }



    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.33");

    static RepositoryService service;

    @BeforeAll
    static void start(){
        Startables.deepStart(Stream.of(mysql)).join();
    }

    @AfterAll
    static void ends(){
        //mysql.stop();
    }


}
