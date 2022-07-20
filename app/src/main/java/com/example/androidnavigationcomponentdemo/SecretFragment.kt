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

        binding = FragmentSecretBinding.bind(view)

        binding.btnCloseSecret.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnCloseWholeBox.setOnClickListener{
            findNavController().popBackStack(R.layout.fragment_root, false)
        }

    }

}