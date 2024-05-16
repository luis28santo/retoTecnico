package com.org.lasantos.api.fast.config;

import com.org.lasantos.api.fast.repositories.ClientRepository;
import com.org.lasantos.api.fast.services.ClientService;
import com.org.lasantos.api.fast.services.EncryptService;
import com.org.lasantos.api.fast.servicesImpl.ClientCacheServiceImpl;
import com.org.lasantos.api.fast.servicesImpl.ClientServiceImpl;
import com.org.lasantos.api.fast.servicesImpl.EncryptServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ConfigBean {

    //    private final ClientRepository clientRepository;
//
//    public ConfigBean(ClientRepository clientRepository) {
//        this.clientRepository = clientRepository;
//    }
    @Bean
    public EncryptService encryptService() {
        return new EncryptServiceImpl();
    }

    @Bean("clientCacheService")
    public ClientService clientCacheService(ClientRepository clientRepository) {
        return new ClientCacheServiceImpl(clientRepository);
    }

    @Bean("clientMysqlService")
    @Primary
    public ClientService clientMysqlService(ClientRepository clientRepository, EncryptService encryptService) {
        return new ClientServiceImpl(clientRepository, encryptService);
    }


}
