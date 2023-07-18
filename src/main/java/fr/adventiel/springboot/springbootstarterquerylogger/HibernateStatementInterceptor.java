package fr.adventiel.springboot.springbootstarterquerylogger;

import org.hibernate.resource.jdbc.spi.StatementInspector;

public class HibernateStatementInterceptor implements StatementInspector {

    private static final ThreadLocal<Long> queryCount = new ThreadLocal<>();

    @Override
    public String inspect(String sql) {
        Long count = queryCount.get();
        if (count != null) {
            queryCount.set(count + 1);
        }

        return sql;
    }

    public Long getQueryCount() {
        return queryCount.get();
    }

    public void startCounter() {
        queryCount.set(0L);
    }

    public void stopCounter() {
        queryCount.remove();
    }
}
