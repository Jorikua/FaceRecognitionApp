package com.example.facerecognitionapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.FaceRect
import com.example.domain.usecase.CalculateFacesUseCase
import com.example.facerecognitionapp.base.BaseViewModel
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val calculateFacesUseCase: CalculateFacesUseCase
): BaseViewModel<DetailsState>(DetailsState()) {

    val rects = MutableLiveData<List<FaceRect>>()

    fun setImageRes(imageRes: Int) {
        state.value = currentState.copy(imageRes = imageRes)
    }

    fun calculateFace(targetWidth: Int, targetHeight: Int, imageRes: Int) {
        isLoading(true)
        viewModelScope.launch {
            val params = CalculateFacesUseCase.Params(targetWidth, targetHeight, imageRes)
            calculateFacesUseCase.invoke(params)
                .handleSuccess {
                    rects.value = it
                }
        }
    }

}