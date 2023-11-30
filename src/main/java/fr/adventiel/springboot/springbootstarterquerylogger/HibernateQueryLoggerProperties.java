package fr.adventiel.springboot.springbootstarterquerylogger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "adventiel.hibernatequerylogger")
public class HibernateQueryLoggerProperties {
    /**
     * Enable query count and time spent log for GET, POST, PUT, DELETE Spring controllers.
     */
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
