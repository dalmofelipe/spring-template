package com.dalmofelipe.springtemplate.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.dalmofelipe.springtemplate.records.LocationRecord;

@Service
public class IpApiRestClientService {
    
    private final String baseURL = "https://ipapi.co/json";
    private final RestClient restClient;

    public IpApiRestClientService() {
        this.restClient = RestClient.builder()
            .baseUrl(baseURL)
            .build();
    }

    public LocationRecord getLocation() {
        return this.restClient
            .get().retrieve().body(LocationRecord.class);
    }

}
