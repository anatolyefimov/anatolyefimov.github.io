package com.nodepipes.core.config

import com.nodepipes.core.api.Router
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class WebConfig {

    @Bean
    fun routerFunction(router: Router): RouterFunction<ServerResponse> {
        return router.graphInvocation()
    }

}