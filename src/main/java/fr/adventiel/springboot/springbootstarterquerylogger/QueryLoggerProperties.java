package fr.adventiel.springboot.springbootstarterquerylogger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.data.querylogger")
public class QueryLoggerProperties {
    private boolean enabled = true;
}
