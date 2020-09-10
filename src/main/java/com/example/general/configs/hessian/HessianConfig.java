package com.example.general.configs.hessian;

import com.example.general.service.abst.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfig {

    @Autowired
    private CardService cardService;

    @Bean(name = "/interconnect/cardService")
    public RemoteExporter remoteExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(cardService);
        exporter.setServiceInterface(CardService.class);
        return exporter;
    }
}