package fr.adventiel.springboot.springbootstarterquerylogger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HibernateQueryLoggerProperties.class)
@ConditionalOnProperty(value = "adventiel.hibernatequerylogger.enabled")
public class QueryLoggerAutoconfiguration {

    @Bean
    public HibernateStatementInterceptor hibernateStatementInterceptor() {
        return new HibernateStatementInterceptor();
    }

    @Bean
    public RegisterInterceptor registerInterceptor() {
        return new RegisterInterceptor(hibernateStatementInterceptor());
    }

    @Bean
    public HibernateQueryLogger hibernateQueryLogger() {
        return new HibernateQueryLogger(hibernateStatementInterceptor());
    }
}
