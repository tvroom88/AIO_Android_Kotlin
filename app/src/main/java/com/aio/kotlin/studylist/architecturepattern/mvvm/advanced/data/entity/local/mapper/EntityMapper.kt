package com.aio.kotlin.studylist.architecturepattern.mvvm.advanced.data.entity.local.mapper

interface EntityMapper<Domain, Entity> {
    fun asEntity(domain: Domain): Entity
    fun asDomain(entity: Entity): Domain
}
