package com.lmntrix.engine.configs

import org.mapstruct.MapperConfig
import org.mapstruct.MappingInheritanceStrategy
import org.mapstruct.ReportingPolicy


@MapperConfig(
    componentModel = "spring", // this actually adds the @Component annotation on the generated Impl
    unmappedTargetPolicy = ReportingPolicy.IGNORE,  // this setting is the reason I define the ignored properties
    mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG
)
interface MapperBeanConfig {

}