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
public class BeforeOrderedFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Order number -1 BeforeOrderedFilter "); //before 로직
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    log.info("BeforeOrderedFilter END "); //after 로직
                }));
    }

    //우선순위
    @Override
    public int getOrder() {
        return -1;
    }
}
