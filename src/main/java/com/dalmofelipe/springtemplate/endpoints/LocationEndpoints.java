package com.dalmofelipe.springtemplate.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dalmofelipe.springtemplate.records.LocationRecord;
import com.dalmofelipe.springtemplate.services.IpApiRestClientService;

@RestController
@RequestMapping(value = "/location/rest-client")
public class LocationEndpoints {

    @Autowired
    private IpApiRestClientService ipApiRestClientService;

    @GetMapping
    public ResponseEntity<LocationRecord> getLocation() {
        var location = this.ipApiRestClientService.getLocation();

        return ResponseEntity.ok().body(location);
    }

}
