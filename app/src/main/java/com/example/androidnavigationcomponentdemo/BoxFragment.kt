package com.example.androidnavigationcomponentdemo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.androidnavigationcomponentdemo.databinding.FragmentBoxBinding
import kotlin.random.Random

class BoxFragment : Fragment(R.layout.fragment_box) {

    private lateinit var binding: FragmentBoxBinding

    private val args: BoxFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // bind created view of fragment to binding
        binding = FragmentBoxBinding.bind(view)

        // set background color from argument value
        binding.root.setBackgroundColor(args.colorValue)

        setOnClickListeners()

    }

    private fun setOnClickListeners() {

        binding.btnGoBack.setOnClickListener{
            findNavController().popBackStack()
        }

        binding.btnOpenSecret.setOnClickListener{

            findNavController().navigate(

                BoxFragmentDirections.actionBoxFragmentToSecretFragment(),

                // additional options to animate navigation
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_exit_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                    }
                }
            )
        }

        binding.btnGenerateNumber.setOnClickListener{

            val generatedNumber = Random.nextInt(100)

            // send result
            findNavController()
                .previousBackStackEntry?.savedStateHandle?.set(TAG_RANDOM_NUMBER_VALUE, generatedNumber)

            // return to previous screen
            findNavController().popBackStack()
        }

    }

    companion object {
        const val TAG_RANDOM_NUMBER_VALUE = "RANDOM_NUMBER_VALUE"
    }

}