package com.msdeneme.newms.core.dao;

import com.msdeneme.newms.core.domain.Equity;
import com.msdeneme.newms.utility.MsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by myalcinsoy on 11-Sep-18.
 */
@Repository
public class EquityDao  implements GenericDao<Equity> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static String INSERT_STR = "INSERT INTO EQUITY ( name, status, value, created_date) values( ?, ? , ? , ?)";

    private static String FIND_BY_NAME = "SELECT * FROM EQUITY WHERE NAME = ? ORDER BY CREATED_DATE DESC" ;

    private static String FIND_PRE_BY_NAME = "SELECT * FROM EQUITY WHERE NAME = ? AND ID < ? ORDER BY CREATED_DATE DESC " ;

    public static final BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper(Equity.class);

    @Override
    public Equity findById(long id) {
        return null;
    }

    public Equity findLastByName(String name) {
        List<Equity> equityList = jdbcTemplate.query(FIND_BY_NAME, new Object[]{name}, rowMapper);
        if ( equityList != null)
            return equityList.get(0);
        return null;
    }

    public Equity findPreLastByName(String name, long id) {
        List<Equity> equityList = jdbcTemplate.query(FIND_PRE_BY_NAME, new Object[]{name, id}, rowMapper);
        if ( equityList != null && equityList.size() > 0 )
            return equityList.get(0);
        return null;
    }


    @Override
    public List<Equity> findAll() {

        return null;
    }

    @Override
    public void insert(Equity entity) {

        jdbcTemplate.update(INSERT_STR, entity.getName(), entity.getStatus(), entity.getValue(), entity.getCreated_date());
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
