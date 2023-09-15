package com.iagocarvalho.activelife.screens.profileScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import com.iagocarvalho.activelife.firebaserepository.DataSorce
import com.iagocarvalho.activelife.model.modelUsers.ModelUser
import kotlinx.coroutines.launch

class ProfileScreenViewModel: ViewModel() {

    val state = mutableStateOf(ModelUser())
    private val dataStore =  DataSorce()
    private val authRepo = AuthRepository()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch { state.value = dataStore.getDataUsersFromFireStore() }
    }

    fun UpdateUserFromFB(
        documenteId: String,
        campo: String,
        valor: String,
    ){
        viewModelScope.launch { dataStore.updateUserFromFb(documenteId, campo, valor) }
    }

    fun DeleteUserFB(){
        authRepo.deleteUser()
    }

    fun SingOut(){
        authRepo.singOut()
    }
}