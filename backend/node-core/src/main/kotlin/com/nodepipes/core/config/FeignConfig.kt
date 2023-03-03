package com.nodepipes.core.config

import com.nodepipes.core.client.ConnectionClient
import com.nodepipes.core.client.GraphClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactivefeign.webclient.WebReactiveFeign

@Configuration
class FeignConfig(
    val config: ExternalServicesConfig
) {

    //TODO: remove as soon we migrate to cloud

    @Bean
    fun getConnectionClient(): ConnectionClient.ConnectionClientFeign {
        return WebReactiveFeign.builder<ConnectionClient.ConnectionClientFeign>()
            .target(ConnectionClient.ConnectionClientFeign::class.java, "http://${config.nodeConf.url}/v1/connections")
    }

    @Bean
    fun getGraphClient(): GraphClient.GraphClientFeign {
        return WebReactiveFeign.builder<GraphClient.GraphClientFeign>()
            .target(GraphClient.GraphClientFeign::class.java, "http://${config.nodeConf.url}/v1/graphs")
    }

}