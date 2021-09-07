package com.example.facerecognitionapp.ui.home

import androidx.lifecycle.viewModelScope
import com.example.base.network.DataException
import com.example.base.resource.ResourceManager
import com.example.domain.model.ProcessedPerson
import com.example.domain.usecase.GetPersonsUseCase
import com.example.facerecognitionapp.R
import com.example.facerecognitionapp.base.BaseViewModel
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPersonsUseCase: GetPersonsUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel<HomeState>(HomeState()) {

    val goToDetailsEvent = LiveEvent<Int>()

    fun getPersons() {
        isLoading(true)
        viewModelScope.launch {
            getPersonsUseCase.invoke(Unit)
                .handleSuccess {
                    state.value = currentState.copy(
                        persons = it
                    )
                }
        }
    }

    fun goToDetails(it: ProcessedPerson) {
        if (it.width == null) {
            exception.value = DataException.UnknownException(resourceManager.getString(R.string.no_face_detected))
            return
        }

        goToDetailsEvent.value = it.imageRes
    }
}