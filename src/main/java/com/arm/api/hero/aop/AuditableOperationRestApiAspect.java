package com.arm.api.hero.aop;

import com.arm.api.hero.component.AppHelper;
import com.arm.api.hero.model.dto.response.GenericResp;
import com.arm.api.hero.util.Constant;
import com.arm.api.hero.util.OperationTimer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Aspect
@Component
public class AuditableOperationRestApiAspect {

    private Logger logger = LoggerFactory.getLogger(AuditableOperationRestApiAspect.class);

    @Autowired
    private AppHelper appHelper;

    @Around("@annotation(AuditableOperationRestApi)")
    public Object audit(ProceedingJoinPoint joinPoint) throws Throwable {
        OperationTimer operationTimer = null;
        ResponseEntity<Object> result = null;
        try {
            operationTimer = OperationTimer.start();

            result = (ResponseEntity<Object>) joinPoint.proceed();

            operationTimer.finish();

            logEndOperation(joinPoint, result, operationTimer);

            return result;
        } catch (Exception ex) {
            if (result == null) {
                GenericResp genericResp = new GenericResp();

                genericResp.setResult(null);
                genericResp.setException(ex);
                genericResp.setMessage(ex.getMessage());

                result = new ResponseEntity<Object>(genericResp, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            logEndOperation(joinPoint, result, operationTimer);

            return result;
        } finally {
            operationTimer = null;
            result = null;
        }
    }

    private void logEndOperation(ProceedingJoinPoint joinPoint, ResponseEntity<Object> result, OperationTimer operationTimer) {
        HashMap<String, Object> dataToLog;
        try {
            dataToLog = getValuesToLoggingEndOperation(joinPoint, result, operationTimer);
            logger.info(appHelper.objectToJson(dataToLog));
        } catch (Exception ex) {
            logger.info("Error en logEndOperation: " + ex.getMessage());
        } finally {
            dataToLog = null;
        }
    }

    private HashMap<String, Object> getValuesToLoggingEndOperation(ProceedingJoinPoint joinPoint, ResponseEntity<Object> result, OperationTimer operationTimer) {
        HashMap<String, Object> hmResult = null;
        MethodSignature signature = null;
        Method method = null;
        AuditableOperationRestApi auditableOperationRestApi = null;
        try {
            hmResult = new HashMap<>();

            signature = (MethodSignature) joinPoint.getSignature();
            method = signature.getMethod();
            auditableOperationRestApi = method.getAnnotation(AuditableOperationRestApi.class);

            hmResult.put(Constant.AppEnum.LOGGER_FIELD_OPERATION.getValue(), auditableOperationRestApi.operation());
            hmResult.put(Constant.AppEnum.LOGGER_FIELD_ELAPSED_MS.getValue(), operationTimer.duration());
            hmResult.put(Constant.AppEnum.LOGGER_HTTP_CODE.getValue(), result.getStatusCode().value());
            hmResult.put(Constant.AppEnum.LOGGER_RESULT.getValue(), (result.getBody() == null ? "" : result.getBody().toString()));

            return hmResult;
        } finally {

        }
    }


}
