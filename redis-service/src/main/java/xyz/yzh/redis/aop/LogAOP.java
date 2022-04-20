package xyz.yzh.redis.aop;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author simple
 */
@Aspect
@Slf4j
@Component
public class LogAOP {

    /**
     *  整个表达式可以分为五个部分：
     *
     *  1、execution(): 表达式主体。
     *
     *  2、第一个*号：表示返回类型，*号表示所有的类型。
     *
     *  3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     *
     *  4、第二个*号：表示类名，*号表示所有的类。
     *
     *  5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     *
     *  execution(<修饰符模式>? <返回类型模式> <方法名模式>(<参数模式>) <异常模式>?)  除了返回类型模式、方法名模式和参数模式外，其它项都是可选的
     *
     */
    @Around("execution (* xyz.yzh.redis.controller..*.*(..))")
    public Object test(ProceedingJoinPoint point) throws Throwable {
        String class_name = point.getTarget().getClass().getName();
        String method_name = point.getSignature().getName();

        var fields = getNameAndValue(point);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(class_name).append(" : ").append(method_name).append("(");
        fields.forEach((k, v) -> {
            stringBuilder.append(k).append(" => ").append(v);
        });
        stringBuilder.append(")");
        log.info(stringBuilder.toString());

        return point.proceed();
    }

    Map<String, Object> getNameAndValue(ProceedingJoinPoint joinPoint) {
        Map<String, Object> param = new HashMap<>();

        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();

        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }

        return param;
    }

}
