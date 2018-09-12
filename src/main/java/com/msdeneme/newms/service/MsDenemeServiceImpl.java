package com.msdeneme.newms.service;

import com.msdeneme.newms.controller.core.BaseRestResponse;
import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "MsDenemeService")
public class MsDenemeServiceImpl implements MsDenemeService {

    private static final Logger logger = LoggerFactory.getLogger(MsDenemeServiceImpl.class);

    @Autowired
    PlatformPropertyService platformPropertyService;
    @Override
    public ItemStatusResponse getItemStatus(ItemStatusRequest request) {
        ItemStatusResponse response = new ItemStatusResponse();

        response.setStatus("BUY");
        response.setMessage("Message");
        response.setValue("12.21");

        return response;
    }

}
