package fr.adventiel.springboot.springbootstarterquerylogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class HibernateQueryLogger {

    private static final Logger logger = LoggerFactory.getLogger(HibernateQueryLogger.class);

    private final HibernateStatementInterceptor hibernateStatementInterceptor;

    public HibernateQueryLogger(HibernateStatementInterceptor hibernateStatementInterceptor) {
        this.hibernateStatementInterceptor = hibernateStatementInterceptor;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void pointCutForGetController() {
        // Pointcut targetting GetMapping controller methods.
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void pointCutForPostController() {
        // Pointcut targetting GetMapping controller methods.
    }

    @Around("pointCutForGetController() || pointCutForPostController()")
    public Object logQueryCount(ProceedingJoinPoint joinPoint) throws Throwable {
        hibernateStatementInterceptor.startCounter();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            stopWatch.stop();
            logger.info("Number of queries executed: {}", hibernateStatementInterceptor.getQueryCount());
            logger.info("Time spent: {} ms", stopWatch.getTotalTimeMillis());
            hibernateStatementInterceptor.stopCounter();
        }
    }
}
