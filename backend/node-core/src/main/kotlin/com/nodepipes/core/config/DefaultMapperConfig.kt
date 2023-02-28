package com.nodepipes.core.config

import org.mapstruct.InjectionStrategy
import org.mapstruct.MapperConfig
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@MapperConfig(
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface DefaultMapperConfig