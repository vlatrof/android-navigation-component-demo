package com.example.androidnavigationcomponentdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidnavigationcomponentdemo.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRootBinding.bind(view)

        binding.btnOpenYellowBox.setOnClickListener{
            openBox(Color.rgb(255, 255, 200))
        }

        binding.btnOpenGreenBox.setOnClickListener{
            openBox(Color.rgb(200, 255, 200))
        }

        parentFragmentManager.setFragmentResultListener(
            BoxFragment.RANDOM_NUMBER_REQUEST_CODE, viewLifecycleOwner) { _, data ->

            val number = data.getInt(BoxFragment.RANDOM_NUMBER_VALUE)
            Toast.makeText(requireContext(), "Generated number: $number", Toast.LENGTH_SHORT).show()
        }

    }

    private fun openBox(color: Int) {

        findNavController().navigate(
            R.id.action_rootFragment_to_boxFragment,
            bundleOf(BoxFragment.TAG_ARG_COLOR to color)
        )

    }

}