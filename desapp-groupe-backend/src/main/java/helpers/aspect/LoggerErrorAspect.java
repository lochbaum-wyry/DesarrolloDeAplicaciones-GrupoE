package helpers.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

    @Aspect
    @Order(1)
    public class LoggerErrorAspect {

        public static Logger logger = Logger.getLogger(LoggerErrorAspect.class);

        @AfterThrowing(pointcut = "execution(* domain.servicesRest*.*(..))",throwing = "e")
        public void logAfter(JoinPoint joinPoint, Throwable e) {
            logger.error("EXCEPTION THROWN: " + e.toString() +
                    " | AT CLASS: " + joinPoint.getTarget().toString());
            e.printStackTrace();
        }
    }
