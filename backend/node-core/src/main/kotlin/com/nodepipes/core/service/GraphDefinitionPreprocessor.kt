package com.nodepipes.core.service

import com.nodepipes.core.domain.model.node.Graph
import com.nodepipes.core.domain.preprocessing.GraphDefinition
import reactor.core.publisher.Mono

interface GraphDefinitionPreprocessor {

    fun process(graph: Graph): Mono<GraphDefinition>

}