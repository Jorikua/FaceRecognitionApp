package com.example.facerecognitionapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.facerecognitionapp.R
import com.example.facerecognitionapp.base.BaseViewModelFragment
import com.example.facerecognitionapp.databinding.FragmentHomeBinding
import com.example.facerecognitionapp.divider
import com.example.facerecognitionapp.person
import kotlin.reflect.KClass

class HomeFragment: BaseViewModelFragment<HomeViewModel, FragmentHomeBinding>() {
    override fun viewModelClass(): KClass<HomeViewModel> = HomeViewModel::class

    override fun layout(): Int = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.getPersons()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        vm.state.observe(viewLifecycleOwner) { state ->
            binding.rv.withModels {
                state.persons.forEach {
                    person {
                        id(it.name)
                        name(it.name)
                        res(it.imageRes)

                        onClick { _ ->
                            vm.goToDetails(it)
                        }
                    }
                    divider {
                        id("divider${it.name}")
                    }
                }
            }
        }

        vm.goToDetailsEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(HomeFragmentDirections.toDetailsFragment(it))
        }
    }
}