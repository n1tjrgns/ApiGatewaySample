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
public class CommonFilter extends AbstractGatewayFilterFactory<CommonFilter.ConfigField> {

    public CommonFilter(){
        super(ConfigField.class);
    }

    @Override
    public GatewayFilter apply(ConfigField configField) {
        return ((exchange, chain) -> {
            log.info("CommonFilter :"+ configField.toString());
            log.info("CommonFilter Message : "+ configField.getBaseMessage());

            if (configField.isPreLogger()){
                log.info("CommonFilter START : " + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(() ->{
                if (configField.isPostLogger()){
                    log.info("CommonFilter END : " + exchange.getResponse());
                }
            }));
        });
    }

    @Getter @Setter
    public static class ConfigField{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}

