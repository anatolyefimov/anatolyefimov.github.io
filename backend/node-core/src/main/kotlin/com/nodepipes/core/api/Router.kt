package com.nodepipes.core.api

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class Router(private val handler: GraphInvocationHandler) {

    fun graphInvocation() = router {
        with(handler) {
            (path(GRAPH_INVOCATION_PATH) and method(HttpMethod.POST)).invoke(::process)
        }
    }


    companion object {
        const val GRAPH_INVOCATION_PATH = "v1/graph/{graphId}"
    }

}