package com.msdeneme.newms.core.dao;

import com.msdeneme.newms.core.domain.MsServiceLog;
import com.msdeneme.newms.utility.Constants;
import com.msdeneme.newms.utility.MsQueries;
import com.msdeneme.newms.utility.MsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MsServiceLogDao implements GenericDao<MsServiceLog> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(MsServiceLog.class);

    @Override
    public MsServiceLog findById(long id) {
        return null;
    }

    @Override
    public List<MsServiceLog> findAll() {
        return null;
    }

    @Override
    public void insert(MsServiceLog entity) {
        if( ! MsUtil.isNullOrEmptyString(entity.getRequest()) && entity.getRequest().length()>= Constants.LOG_MAX_SIZE)
            entity.setRequest(entity.getRequest().substring(0,Constants.LOG_MAX_SIZE));

        if( ! MsUtil.isNullOrEmptyString(entity.getResponse()) && entity.getResponse().length()>= Constants.LOG_MAX_SIZE)
            entity.setResponse(entity.getResponse().substring(0, Constants.LOG_MAX_SIZE));

        jdbcTemplate.update(MsQueries.INSERT_APP_SERVICE_LOG, entity.getServiceName(), entity.getRequest(),
                entity.getResponse(), entity.getIp(), entity.getUseragent(), entity.getCreatedDate());
    }

    @Override
    public int update(MsServiceLog entity) {
        return 0;
    }

    @Override
    public int delete(MsServiceLog entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }

    public void validationQuery() {
        jdbcTemplate.execute(MsQueries.VALIDATION_QUERY);
    }
}
