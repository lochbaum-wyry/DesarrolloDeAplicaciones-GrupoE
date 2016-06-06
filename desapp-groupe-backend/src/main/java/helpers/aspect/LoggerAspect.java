package helpers.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

    private Logger log = Logger.getLogger(getClass());

    //busca todos los metodos publicos del paquete XX y que tengan la annotacion Loggeable
    //  @After("execution(public * ar.edu.unq.desapp.grupoA.services.*.*(..)) && @annotation(Loggable)")
    //@After("execution(* *(..)) && @annotation(pablin)")
    //@After("execution (@Loggeable public * ar.edu.unq.desapp.grupoA.services.*.*(..))")
    @After("execution(public * domain.services.UserService.signUp*.*(..))")
    public void logAfter(JoinPoint pjp) {
        //public void logAfter(JoinPoint pjp, Loggable loggable) {
        log.info(pjp.getSignature().getName() + " Se hizo el signUp");
    }

}
