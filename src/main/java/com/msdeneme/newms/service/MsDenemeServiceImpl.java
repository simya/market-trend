package com.msdeneme.newms.service;

import com.msdeneme.newms.controller.core.BaseRestResponse;
import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.request.ItemUpdateRequest;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;
import com.msdeneme.newms.core.dao.EquityDao;
import com.msdeneme.newms.core.domain.Equity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;

@Service(value = "MsDenemeService")
public class MsDenemeServiceImpl implements MsDenemeService {

    private static final Logger logger = LoggerFactory.getLogger(MsDenemeServiceImpl.class);

    @Autowired
    PlatformPropertyService platformPropertyService;

    @Autowired
    EquityDao equityDao;

    @Override
    public ItemStatusResponse getItemStatus(ItemStatusRequest request) {
        ItemStatusResponse response = new ItemStatusResponse();

        Equity equity = equityDao.findLastByName(request.getEquity());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        DecimalFormat df;
        if ( equity.getValue().doubleValue() > 10 )
            df = new DecimalFormat("###,###.00");
        else
            df = new DecimalFormat("##.0000");

        if ( "BUY".equals(equity.getStatus())) {

            response.setStatus("UP");

            response.setMessage("BUY " + equity.getName() + " \n PRICE : " +
                    df.format(equity.getValue()) + "\n DATE : " +
                    sdf.format(equity.getCreated_date()));
        }
        else {
            response.setStatus("WAIT");

            Equity preEquity = equityDao.findPreLastByName(request.getEquity(), equity.getId());
            if ( preEquity != null ) {
                double percentage = ( equity.getValue().doubleValue() - preEquity.getValue().doubleValue() ) / preEquity.getValue().doubleValue();
                response.setMessage("SELL " + equity.getName() + " \n PRICE : " +
                        df.format(equity.getValue()) + "\n DATE : " +
                        sdf.format(equity.getCreated_date()));
            }

        }


        return response;
    }

    @Override
    public BaseRestResponse updateItemStatus(ItemUpdateRequest request){
        BaseRestResponse response = new BaseRestResponse();

        Equity equity = new Equity();
        equity.setStatus(request.getStatus());
        equity.setName(request.getEquity());
        equity.setValue(new BigDecimal(request.getValue()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            equity.setCreated_date(simpleDateFormat.parse(request.getUpdateDate()));
        } catch (Exception e){
            ;
        }
        equityDao.insert(equity);

        return response;
    }

}
