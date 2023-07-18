package fr.adventiel.springboot.springbootstarterquerylogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RegisterInterceptor implements HibernatePropertiesCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(RegisterInterceptor.class);

    private final HibernateStatementInterceptor hibernateStatementInterceptor;

    public RegisterInterceptor(HibernateStatementInterceptor hibernateStatementInterceptor) {
        this.hibernateStatementInterceptor = hibernateStatementInterceptor;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put("hibernate.session_factory.statement_inspector", hibernateStatementInterceptor);
    }
}
