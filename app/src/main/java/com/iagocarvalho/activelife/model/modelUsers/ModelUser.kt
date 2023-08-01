package com.iagocarvalho.activelife.model.modelUsers

data class ModelUser(
    val user_id: String = "",
    val display_name: String= "",
    val peso: String= "",
    val altura: String= "",
    val idade: String= "",
    val email: String= "",
    val avatarURL: String= "",


){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.user_id,
            "display_name" to this.display_name,
            "peso" to this.peso,
            "altura" to this.altura,
            "idade" to this.idade,
            "email" to this.email,
            "avatar_Url" to this.avatarURL,
        )
    }
}
