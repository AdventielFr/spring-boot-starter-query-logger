package fr.adventiel.springboot.springbootstarterquerylogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(QueryLoggerProperties.class)
@ConditionalOnProperty(value = "spring.data.querylogger.enabled", matchIfMissing = true)
public class QueryLoggerAutoconfiguration {

    private static final Logger logger = LoggerFactory.getLogger(QueryLoggerAutoconfiguration.class);

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
