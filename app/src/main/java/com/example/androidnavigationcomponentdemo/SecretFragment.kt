package com.example.androidnavigationcomponentdemo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidnavigationcomponentdemo.databinding.FragmentSecretBinding

class SecretFragment : Fragment(R.layout.fragment_secret) {

    private lateinit var binding: FragmentSecretBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {

        binding.btnCloseSecret.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnCloseWholeBox.setOnClickListener{
            findNavController().popBackStack(R.id.rootFragment, false)
        }

    }

    private fun initBinding(view: View) {
        binding = FragmentSecretBinding.bind(view)
    }

}