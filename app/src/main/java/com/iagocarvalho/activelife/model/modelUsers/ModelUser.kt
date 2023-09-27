package com.iagocarvalho.activelife.model.modelUsers

data class ModelUser(
    val user_id: String = "",
    val display_name: String= "",
    val peso: String= "",
    val altura: String= "",
    val idade: String= "",
    val email: String= "",
    val avatarURL: String= "",
    val documenteId: String = ""
)
