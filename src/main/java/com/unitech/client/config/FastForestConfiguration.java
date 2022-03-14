package com.unitech.client.config;

import com.unitech.client.FastForestClient;
import feign.codec.ErrorDecoder;
import feign.error.AnnotationErrorDecoder;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(clients = {FastForestClient.class})
public class FastForestConfiguration {


    @Bean
    public ErrorDecoder errorDecoder() {

        return AnnotationErrorDecoder.builderFor(FastForestClient.class)
                .withResponseBodyDecoder(new JacksonDecoder())
                .build();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }
}
