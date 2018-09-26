package com.msdeneme.newms.service;

import com.msdeneme.newms.controller.core.BaseRestRequest;
import com.msdeneme.newms.controller.core.BaseRestResponse;
import com.msdeneme.newms.controller.core.request.ItemStatusRequest;
import com.msdeneme.newms.controller.core.request.ItemUpdateRequest;
import com.msdeneme.newms.controller.core.response.ItemListResponse;
import com.msdeneme.newms.controller.core.response.ItemStatusResponse;


public interface MsDenemeService {

    ItemListResponse getItemList(BaseRestRequest request);

    ItemStatusResponse getItemStatus(ItemStatusRequest request);

    BaseRestResponse updateItemStatus(ItemUpdateRequest request);

}
