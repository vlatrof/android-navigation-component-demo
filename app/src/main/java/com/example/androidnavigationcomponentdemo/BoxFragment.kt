package com.example.androidnavigationcomponentdemo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidnavigationcomponentdemo.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentBoxBinding.bind(view)

        val color = requireArguments().getInt(TAG_ARG_COLOR)

        binding.root.setBackgroundColor(color)

        binding.btnGoBack.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnOpenSecret.setOnClickListener{
            findNavController().navigate(R.id.action_boxFragment_to_secretFragment)
        }

        binding.btnGenerateNumber.setOnClickListener{
            val generatedNumber = Random.nextInt(100)
            parentFragmentManager.setFragmentResult(
                RANDOM_NUMBER_REQUEST_CODE,
                bundleOf(RANDOM_NUMBER_VALUE to generatedNumber)
            )
            findNavController().popBackStack()
        }

    }

    companion object {
        const val TAG_ARG_COLOR = "color"
        const val RANDOM_NUMBER_REQUEST_CODE = "RANDOM_NUMBER_REQUEST_CODE"
        const val RANDOM_NUMBER_VALUE = "RANDOM_NUMBER_VALUE"
    }

}