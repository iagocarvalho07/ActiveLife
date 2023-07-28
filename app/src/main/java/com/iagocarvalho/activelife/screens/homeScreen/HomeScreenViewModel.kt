package com.iagocarvalho.activelife.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.AuthRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    fun sigOut(){
        viewModelScope.launch { AuthRepository().SingOut() }
    }
}