package com.msdeneme.newms.service;

import com.msdeneme.newms.core.dao.AppParamDao;
import com.msdeneme.newms.core.domain.AppParam;
import com.msdeneme.newms.utility.Constants;
import com.msdeneme.newms.utility.MsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

@Service("PlatformPropertyService")
public class PlatformPropertyServiceImpl implements PlatformPropertyService {

    private static Properties platformProperties;

    private static Date lastPlatformPropertiesUpdateDate = new Date(0);

    private final AtomicBoolean platformPropertiesUpdating = new AtomicBoolean(false);

    private static final Logger logger = LoggerFactory.getLogger(PlatformPropertyServiceImpl.class);

    @Autowired
    AppParamDao appParamDao;


    @Override
    public Optional<String> getPlatformProperty(String key) {

        String updateInterval = System.getProperty(Constants.PROPERTY_UPDATE_INTERVAL);
        int updateIntervalTime = Integer.parseInt(updateInterval == null ? Constants.PROPERTY_UPDATE_INTERVAL_TIME : updateInterval);
        if (platformProperties == null || new Date().getTime() - lastPlatformPropertiesUpdateDate.getTime() > updateIntervalTime && platformPropertiesUpdating.compareAndSet(false, true)) {

            Optional<List<AppParam>> appParams = appParamDao.findAll();

            Properties properties = new Properties();
            appParams.ifPresent(x -> x.forEach(appParam -> {
                if (!MsUtil.isNullOrEmptyString(appParam.getName()) && !MsUtil.isNullOrEmptyString(appParam.getValue())) {
                    properties.put(appParam.getName(), appParam.getValue());
                } else {
                    logger.info("this application parameter is null");
                }
            }));

            platformProperties = properties;
            lastPlatformPropertiesUpdateDate = new Date();

            platformPropertiesUpdating.set(false);

            logger.info("application parameters are taken from db");

        }
        if (!MsUtil.isNullOrEmptyString(key) && platformProperties.containsKey(key)) {
            return Optional.ofNullable(platformProperties.getProperty(key));
        }
        return Optional.empty();
    }


    @Override
    public boolean isTestEnvironment() {

        Optional<String> environment = getPlatformProperty(Constants.ENVIRONMENT);
        if (environment.isPresent()) {
            return environment.get().equals(Constants.TEST_ENVIRONMENT) ? true : false;
        }
        return true;
    }

}