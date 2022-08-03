package config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

@ComponentScan(basePackages = {"controller", "data.dao"})
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    final String USERNAME_PROPERTY = "USERNAME";
    final String PASSWORD_PROPERTY = "PASSWORD";
    final String URL_PROPERTY = "URL";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }

    @Bean
    SessionFactory sessionFactory() {
        try {
            org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration().configure();

            cfg.setProperty("hibernate.connection.url", System.getProperty(URL_PROPERTY));
            cfg.setProperty("hibernate.connection.username", System.getProperty(USERNAME_PROPERTY));
            cfg.setProperty("hibernate.connection.password", System.getProperty(PASSWORD_PROPERTY));

            return cfg.buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @Bean
    Connection connection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Properties info = new Properties();
        info.setProperty("user",
                Objects.requireNonNull(System.getProperty(USERNAME_PROPERTY), "Username property not set."));
        info.setProperty("password",
                Objects.requireNonNull(System.getProperty(PASSWORD_PROPERTY), "Password property not set."));
        info.setProperty("url",
                Objects.requireNonNull(System.getProperty(URL_PROPERTY), "Url property not set."));

        String URL = (String) info.get("url");

        return DriverManager.getConnection(URL, info);
    }
}
