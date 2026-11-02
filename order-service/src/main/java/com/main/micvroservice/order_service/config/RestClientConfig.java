package com.main.micvroservice.order_service.config;

import com.main.micvroservice.order_service.InventoryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestClientConfig {

    @Value("${inventory.url}")
    private String inventoryUrl;

    @Bean
    public InventoryClient inventoryClient(){
        RestClient restClient =  RestClient.builder()
                .baseUrl(inventoryUrl)
                .build();
        var restClientAdapter = RestClientAdapter.create(restClient);
        var build = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return build.createClient(InventoryClient.class);

    }

}
