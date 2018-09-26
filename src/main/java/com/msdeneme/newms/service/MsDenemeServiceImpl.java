package com.msdeneme.newms.service;

import com.msdeneme.newms.controller.core.BaseRestRequest;
import com.msdeneme.newms.controller.core.BaseRestResponse;
import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.request.ItemUpdateRequest;
import com.msdeneme.newms.controller.core.response.ItemListResponse;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;
import com.msdeneme.newms.core.dao.EquityDao;
import com.msdeneme.newms.core.domain.Equity;
import com.msdeneme.newms.utility.MsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service(value = "MsDenemeService")
public class MsDenemeServiceImpl implements MsDenemeService {

    private static final Logger logger = LoggerFactory.getLogger(MsDenemeServiceImpl.class);

    @Autowired
    PlatformPropertyService platformPropertyService;

    @Autowired
    EquityDao equityDao;

    @Override
    public ItemListResponse getItemList(BaseRestRequest request){
        ItemListResponse response = new ItemListResponse();
        List<String> list = Arrays.asList("AUD/USD", "BIST100", "BOVESPA", "BTC/USD", "DAX", "EUR/USD",
                "EURONEXT", "FTSE100", "GBP/USD", "HSI", "NASDAQ",
                "NIKKEI225", "S&P500", "SSEC", "USD/CAD", "USD/JPY", "USD/TRY", "XAU/USD");

        response.setItemList(list);

        return response;
    }

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

        response.setEquity(request.getEquity());
        if ( "BUY".equals(equity.getStatus())) {
            response.setOper("LONG");

            response.setValueLabel(MsUtil.getBuyLabel(request.getLocale()));
            response.setValue(df.format(equity.getValue())) ;
            response.setDateLabel(MsUtil.getDateLabel(request.getLocale()));
            response.setDate(sdf.format(equity.getCreated_date()));
        }
        else {
            response.setOper("FLAT");

            Equity preEquity = equityDao.findPreLastByName(request.getEquity(), equity.getId());
            if ( preEquity != null ) {
                double percentage = ( equity.getValue().doubleValue() - preEquity.getValue().doubleValue() ) / preEquity.getValue().doubleValue() * 100;
                response.setTradeLabel(MsUtil.getTradeLabel(request.getLocale()));
                response.setTrade(new BigDecimal(percentage).setScale(2, BigDecimal.ROUND_HALF_UP) + " % " );
            }

            response.setValueLabel(MsUtil.getSellLabel(request.getLocale()));
            response.setValue(df.format(equity.getValue())) ;
            response.setDateLabel(MsUtil.getDateLabel(request.getLocale()));
            response.setDate(sdf.format(equity.getCreated_date()));
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
