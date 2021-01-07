package com.msa.apigatewaysample.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class Client2Filter extends AbstractGatewayFilterFactory<Client2Filter.ConfigField> {

    public Client2Filter(){
        super(Client2Filter.ConfigField.class);
    }

    @Override
    public GatewayFilter apply(ConfigField configField) {
        return ((exchange, chain) -> {
            log.info("Client2Filter :"+ configField.toString());
            log.info("Client2Filter Message : "+ configField.getBaseMessage());

            if (configField.isPreLogger()){
                log.info("Client2Filter START : " + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                if (configField.isPostLogger()){
                    log.info("Client2Filter END : " + exchange.getResponse());
                }
            }));
        });
    }

    @Getter
    @Setter
    public static class ConfigField{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}

