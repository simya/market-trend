package com.msdeneme.newms.core.dao;

import com.msdeneme.newms.core.domain.AppParam;
import com.msdeneme.newms.utility.MsQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class AppParamDao implements GenericDao<AppParam> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(AppParam.class);

    @Override
    public AppParam findById(long id) {
        return null;
    }

    @Override
    public List<AppParam> findAll() {
        return jdbcTemplate.query(MsQueries.GET_ALL_PUSH_APP_PARAM,rowMapper);
    }

    @Override
    public void insert(AppParam entity) {

    }

    @Override
    public int update(AppParam entity) {
        return 0;
    }

    @Override
    public int delete(AppParam entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }
}
