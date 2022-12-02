package com.example.aptoide.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aptoide.data.local.room.AppEntity
import com.example.aptoide.domain.repo.RepoInterface
import com.example.aptoide.domain.model.Parameters
import com.example.aptoide.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RepoInterface): ViewModel() {
    private var _data: MutableStateFlow<Resource<List<AppEntity>>> = MutableStateFlow(Resource.Loading())
    var data: StateFlow<Resource<List<AppEntity>>> = _data

    var app = Parameters("",0,"", "",
        0,"","","","",0,
        0.0, 0,"","","",
        0,"")

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getApps().collect {
                _data.value = it
            }
        }
    }
}