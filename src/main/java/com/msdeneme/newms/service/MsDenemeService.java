package com.msdeneme.newms.service;

import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;


public interface MsDenemeService {

    ItemStatusResponse getItemStatus(ItemStatusRequest request);

}
