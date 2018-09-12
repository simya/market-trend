package com.msdeneme.newms.aspect;

import com.msdeneme.newms.core.dao.MsServiceLogDao;
import com.msdeneme.newms.core.domain.MsServiceLog;
import com.msdeneme.newms.utility.MsUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Aspect
public class RestAspect {

    private static final Logger logger = LoggerFactory.getLogger(RestAspect.class);

    @Autowired
    MsServiceLogDao msServiceLogDao;

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {}

    @Around("within(@org.springframework.web.bind.annotation.RestController *) && args(request)")
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint, Object request) {

        String requestJson = "";
        String responseJson = "";
        String methodName = "";
        Object response = null;
        Date currentTime = new Date();
        try {
            requestJson = MsUtil.getJsonString(request);

            response = proceedingJoinPoint.proceed();

            responseJson = MsUtil.getJsonString(response);
            methodName = proceedingJoinPoint.getSignature().getName();
            return response;
        } catch (Throwable e) {
            logger.error("around Controller exception : " + ExceptionUtils.getStackTrace(e));
            responseJson = ExceptionUtils.getStackTrace(e);
        } finally {
            MsServiceLog msServiceLog = new MsServiceLog();
            msServiceLog.setRequest(requestJson);
            msServiceLog.setResponse(responseJson);
            msServiceLog.setServiceName(methodName);
            msServiceLog.setIp(MsUtil.getHostIp());
            msServiceLog.setCreatedDate(currentTime);
            msServiceLogDao.insert(msServiceLog);
        }
        return response;
    }
}
