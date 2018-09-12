package com.msdeneme.newms.service;

import java.util.Optional;

/**
 * Created by Akif Hatipoglu on 21.02.2018.
 */
public interface PlatformPropertyService {

    Optional<String> getPlatformProperty(String key);

    boolean isTestEnvironment();
}
