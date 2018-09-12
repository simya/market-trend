package com.msdeneme.newms.controller;

import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;
import com.msdeneme.newms.service.MsDenemeService;
import com.msdeneme.newms.service.PlatformPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    ItemStatusResponse sendPushNotification(@RequestBody ItemStatusRequest request) {
        logger.info("post request for sendPushNotification.do");

        if ( validateRequest(request) )
            return new ItemStatusResponse();


        return msDenemeService.getItemStatus(request);
    }


    private boolean validateRequest(ItemStatusRequest request) {

        return true;
    }


}
