package com.msdeneme.newms.core.dao;

import com.msdeneme.newms.core.domain.Equity;
import com.msdeneme.newms.utility.MsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Created by myalcinsoy on 11-Sep-18.
 */
public class EquityDao  implements GenericDao<Equity> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static String FIND_BY_NAME = "SELECT * FROM EQUITY WHERE NAME = ?" ;

    public static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(Equity.class);

    @Override
    public Optional<Equity> findById(long id) {
        return null;
    }

    public Optional<Equity> findByName(String name) {
        Optional<Equity> equity = MsUtil.findFirst(jdbcTemplate.query(FIND_BY_NAME, new Object[]{name}, rowMapper));

        return equity;
    }

    @Override
    public Optional<List<Equity>> findAll() {
        return null;
    }

    @Override
    public void insert(Equity entity) {

    }

    @Override
    public int update(Equity entity) {
        return 0;
    }

    @Override
    public int delete(Equity entity) {
        return 0;
    }

    @Override
    public void deleteById(long entityId) {

    }

}
