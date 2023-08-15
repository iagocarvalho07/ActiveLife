package com.iagocarvalho.activelife.model.modelUsers

data class ModelExerciceFB(
    val body_Part: String = "",
    val equipment: String = "",
    val gif_url: String = "",
    val id: String = "",
    val name: String = "",
    val target: String = "",
    val treino: String = "",
    val user_Id: String = "",
){

    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "body_Part" to this.body_Part,
            "equipment" to this.equipment,
            "gif_url:" to this.gif_url,
            "id" to this.id,
            "name" to this.name,
            "target" to this.target,
            "treino" to this.treino,
            "user_Id" to this.user_Id,
        )
    }
}
