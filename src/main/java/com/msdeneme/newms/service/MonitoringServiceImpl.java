package com.msdeneme.newms.service;

import com.msdeneme.newms.core.dao.MsServiceLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("MonitoringService")
public class MonitoringServiceImpl implements MonitoringService {

     @Autowired
     MsServiceLogDao msServiceLogDao;

     @Override
     public void healthCheck() {
         msServiceLogDao.validationQuery();
     }
}
