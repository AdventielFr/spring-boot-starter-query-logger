package fr.adventiel.springboot.springbootstarterquerylogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void pointCutForPutController() {
        // Pointcut targetting GetMapping controller methods.
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void pointCutForDeleteController() {
        // Pointcut targetting GetMapping controller methods.
    }

    @Around("pointCutForGetController() || pointCutForPostController() || pointCutForPutController() || pointCutForDeleteController()")
    public Object logQueryCount(ProceedingJoinPoint joinPoint) throws Throwable {
        hibernateStatementInterceptor.startCounter();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            stopWatch.stop();
            if (logger.isInfoEnabled()) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                logger.info("""
                                Call to: {} with arguments: {}.
                                Number of queries executed: {}
                                Time spent: {} ms""",
                        signature.toShortString(),
                        joinPoint.getArgs(),
                        hibernateStatementInterceptor.getQueryCount(),
                        stopWatch.getTotalTimeMillis()
                );
                hibernateStatementInterceptor.stopCounter();
            }
        }
    }
}
