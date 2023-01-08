package com.telkomsel.kezbek.common.interceptor;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.telkomsel.kezbek.common.exception.JoinPointException;

@Aspect
@Configuration
public class ControllerInterceptor extends CommonInterceptor {
    
    private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Override
    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object aroundAdvise(ProceedingJoinPoint jp) throws JoinPointException {
        String reqPath = getRequestPath(jp);
        String serviceName = jp.getTarget().getClass().getSimpleName() + " " + reqPath;
        String rqString = getRequest(jp);
        Map<String, String> additionalInfo = new HashMap<>();

        writeLog(rqString, serviceName, "REQUEST");

        Object returnObj = null;
        try {
            returnObj = jp.proceed();
        } catch (Throwable t) {            
            additionalInfo.put("Exception", t.getMessage());
            throw new JoinPointException(t);
        } finally {
            String rsString = getResponse(returnObj);

            writeLog(rsString, serviceName, "RESPONSE");

        }

        return returnObj;
    }

    private void writeLog(String objString, String serviceName, String type) {
        if (objString != null) {
            if (objString.getBytes(StandardCharsets.UTF_8).length <= MAX_BYTES_LOG_OUTPUT) {
                logger.info("{} [{}]: {} {}", type, serviceName, System.getProperty(LINE_SEPARATOR), objString);
            } else {
                logger.info("{}} [{}] == Input content size is more than {} bytes ==", type,
                        System.getProperty(LINE_SEPARATOR), MAX_BYTES_LOG_OUTPUT);
            }
        } else {
            logger.info("{}} [{}] {} == NULL ==", type, serviceName, System.getProperty(LINE_SEPARATOR));
        }
    }

    private String getResponse(Object object) {
        String resp = null;
        if (object != null) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            try {
                resp = mapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                logger.error("Failed to map response object {} : {}", object, e.getMessage());
            }
        }

        return resp;
    }

    private String getRequest(JoinPoint p) {
        String req = null;
        Object[] signatureArgs = p.getArgs();

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        if (signatureArgs != null && signatureArgs.length > 0) {
            for (Object object : signatureArgs) {
                try {
                    if (object != null) {
                        req = mapper.writeValueAsString(object);
                    }
                } catch (JsonProcessingException e) {
                    logger.error("Failed to map request object {} : {}", object, e.getMessage());
                }

            }
        }

        return req;
    }

    private String getRequestPath(JoinPoint p) {
        String reqPath = null;
        try {
            MethodSignature signature = (MethodSignature) p.getSignature();
            RequestMapping reqMapping = signature.getMethod().getAnnotation(RequestMapping.class);
            reqPath = (reqMapping.value().length > 0) ? reqMapping.value()[0] : "";
        } catch (Exception e) {
            logger.error("Unable to get request path on method {}.{} : {}", p.getTarget().getClass().getSimpleName(),
                    p.getSignature().getName(), e.getMessage());
        }
        return reqPath;
    }
}
