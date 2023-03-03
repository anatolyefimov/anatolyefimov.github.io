package com.nodepipes.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "external-services")
data class ExternalServicesConfig(
    var nodeConf: ExternalService
) {

    data class ExternalService(val url: String)

}