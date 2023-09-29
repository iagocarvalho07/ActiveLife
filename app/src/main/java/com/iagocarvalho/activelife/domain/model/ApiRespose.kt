package com.iagocarvalho.activelife.domain.model


data class ApiRespose(
    val succes: Boolean,
    val message: String? = null,
    val prvPage:Int? = null,
    val nextPage: Int? = null,
    val exercices: List<Exercices> = emptyList()
)
