package com.iagocarvalho.activelife.screens.profileScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagocarvalho.activelife.firebaserepository.DataSorce
import com.iagocarvalho.activelife.model.modelUsers.ModelUser
import kotlinx.coroutines.launch

class ProfileScreenViewModel: ViewModel() {

    val state = mutableStateOf(ModelUser())
    private val dataStore =  DataSorce()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch { state.value = dataStore.getDataUsersFromFireStore() }
    }
}