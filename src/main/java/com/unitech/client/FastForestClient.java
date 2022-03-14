package com.unitech.client;

import com.unitech.client.config.FastForestConfiguration;
import com.unitech.client.error.FastForestException;
import com.unitech.client.model.response.AllRates;
import com.unitech.client.model.response.MultiRate;
import com.unitech.client.model.response.OneRate;
import feign.error.ErrorHandling;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fast-forest", url = "${application.service.fast-forest.url}",
        configuration = FastForestConfiguration.class)
public interface FastForestClient {

    @GetMapping("/fetch-all")
    @ErrorHandling(defaultException = FastForestException.class)
    AllRates getAllRates(@RequestParam String from,
                         @RequestParam String api_key);

    @GetMapping("/fetch-one")
    @ErrorHandling(defaultException = FastForestException.class)
    OneRate getOneRate(@RequestParam String from,
                       @RequestParam String to,
                       @RequestParam String api_key);
    @GetMapping("/fetch-multi")
    @ErrorHandling(defaultException = FastForestException.class)
    MultiRate getMultiRate(@RequestParam String from,
                           @RequestParam String [] to,
                           @RequestParam String api_key);

}
