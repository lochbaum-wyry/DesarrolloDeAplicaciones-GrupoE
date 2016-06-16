package helpers.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.DateTime;

@Aspect
public class LoggerAspect {

    private Logger log = Logger.getLogger(LoggerAspect.class);

    //busca todos los metodos publicos del paquete XX y que tengan la annotacion Loggeable
    //  @After("execution(public * ar.edu.unq.desapp.grupoA.services.*.*(..)) && @annotation(Loggable)")
    //@After("execution(* *(..)) && @annotation(pablin)")
    //@After("execution (@Loggeable public * ar.edu.unq.desapp.grupoA.services.*.*(..))")

    //@After("execution(public * domain.services.UserService.signUp*.*(..))")
    //public void logAfter(JoinPoint pjp) {
    //    //public void logAfter(JoinPoint pjp, Loggable loggable) {
    //    log.info(pjp.getSignature().getName() + " Se hizo el signUp");
    //}

    @After("execution(*  domain.servicesRest.UserServiceRest*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("AT: " + new DateTime());
        log.info("CLASS: " + joinPoint.getTarget().toString() +
                " | METHOD: " + joinPoint.getSignature().getName() +
                " | ARGUMENTS: " + printArguments(joinPoint.getArgs()));
    }

    private static String printArguments(Object[] anArgumentsArray) {
        Object[] arguments = anArgumentsArray;
        String output = "";
        for (Object argument: arguments) {
            output += argument;
        }
        return output;
    }

}
