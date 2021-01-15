package com.msa.apigatewaysample.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BeforeFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global Before Filter executed");
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("BeforeFilter END "); //after 로직
                }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
