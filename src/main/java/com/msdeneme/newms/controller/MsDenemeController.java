package com.msdeneme.newms.controller;

import com.msdeneme.newms.controller.core.BaseRestResponse;
import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.request.ItemUpdateRequest;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;
import com.msdeneme.newms.service.MsDenemeService;
import com.msdeneme.newms.service.PlatformPropertyService;
import com.msdeneme.newms.utility.MsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;


@RestController
@RequestMapping("/rest")
public class MsDenemeController {

    private static final Logger logger = LoggerFactory.getLogger(MsDenemeController.class);

    @Autowired
    MsDenemeService msDenemeService;

    @Autowired
    PlatformPropertyService platformPropertyService;

    @RequestMapping(value = {"/getItemStatus.do"},method = RequestMethod.POST)
    public @ResponseBody
    ItemStatusResponse getItemStatus(@RequestBody ItemStatusRequest request) {
        logger.info("post request for getItemStatus.do");

        if ( ! validateRequest(request) )
            return new ItemStatusResponse();


        return msDenemeService.getItemStatus(request);
    }


    @RequestMapping(value = {"/updateItemStatus.do"},method = RequestMethod.POST)
    public @ResponseBody
    BaseRestResponse updateItemStatus(@RequestBody ItemUpdateRequest request) {
        logger.info("post request for updateItemStatus.do");

        if ( ! validateRequest(request) )
            return new BaseRestResponse();


        return msDenemeService.updateItemStatus(request);
    }


    private boolean validateRequest(ItemStatusRequest request) {

        return true;
    }

    private boolean validateRequest(ItemUpdateRequest request) {

        if ( request != null && ( request.getSign() == null || request.getSign().length() < 8 ))
            return false;

        int signValue = MsUtil.getSignValue();

        while ( signValue > 0 ){
            int mod = signValue % 10;
            if ( Integer.parseInt(""+request.getSign().charAt(mod)) != mod )
                return false;

            signValue = signValue / 10;
        }

        return true;
    }

}
