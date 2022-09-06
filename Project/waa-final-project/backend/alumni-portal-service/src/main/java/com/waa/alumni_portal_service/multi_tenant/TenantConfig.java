package com.waa.alumni_portal_service.multi_tenant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "tenant")
public class TenantConfig {
    public Map<String, String> map;
    TenantConfig(){
        map = new HashMap<>();
        map.put("yahoo.com", "tenant2");
    }
}
