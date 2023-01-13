package com.kjt.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
@Component
@Aspect
public class LoggingAspect {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Before(value = "@annotation(LogRequestBody)")
    public void invoke(JoinPoint joinPoint) throws JsonProcessingException {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        ParameterIndex<RequestBody> parameterIndex = getParamaterIndexOfAnnotatedParameter(method, RequestBody.class);
        Object[] args = joinPoint.getArgs();

        Object requestBody = (Object) args[parameterIndex.getIndex()];
        writeLogs(methodSignature, requestBody);
    }

    @SneakyThrows
    private void writeLogs(MethodSignature methodSignature, Object object) {
        LogRequestBody annotation = AnnotationUtils.getAnnotation(methodSignature.getMethod(), LogRequestBody.class);
        if(annotation.prettify()) {
            log.info("Logging requestBody: {}", OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object));
            return;
        }

        log.info("Logging requestBody: {}", OBJECT_MAPPER.writeValueAsString(object));
    }

    private <T extends Annotation> ParameterIndex getParamaterIndexOfAnnotatedParameter(Method method, Class<T> clazz) {
        Parameter[] parameters = method.getParameters();

        for(int i = 0; i < parameters.length; i++) {
            T t = parameters[i].getAnnotation(clazz);
            if(null == t) {
                continue;
            }
            return new ParameterIndex(i, t, null);
        }
        return null;
    }

}

@Data
@AllArgsConstructor
class ParameterIndex<T extends Annotation> {
    private int index;
    private T annotation;
    private Object value;
}
