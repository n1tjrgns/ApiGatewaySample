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
public class Client1Filter extends AbstractGatewayFilterFactory<Client1Filter.ConfigField> {

    public Client1Filter(){
        super(ConfigField.class);
    }

    @Override
    public GatewayFilter apply(ConfigField configField) {
        return ((exchange, chain) -> {
            log.info("Client1Filter :"+ configField.toString());
            log.info("Client1Filter Message : "+ configField.getBaseMessage());

            if (configField.isPreLogger()){
                log.info("Client1Filter START : " + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                if (configField.isPostLogger()){
                    log.info("Client1Filter END : " + exchange.getResponse());
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

