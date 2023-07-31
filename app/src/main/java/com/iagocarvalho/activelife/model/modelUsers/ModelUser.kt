package com.iagocarvalho.activelife.model.modelUsers

data class ModelUser(
    val userId: String,
    val name: String,
    val peso: String,
    val altura: String,
    val idade: String,
    val email: String,
    val avatarURL: String,


){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user-id" to this.userId,
            "display_name" to this.name,
            "peso" to this.peso,
            "altura" to this.altura,
            "idade" to this.idade,
            "email" to this.email,
            "avatar_Url" to this.avatarURL,
        )
    }
}
