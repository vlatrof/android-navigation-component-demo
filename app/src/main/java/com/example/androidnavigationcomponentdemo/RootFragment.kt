package com.example.androidnavigationcomponentdemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.androidnavigationcomponentdemo.databinding.FragmentRootBinding

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var binding: FragmentRootBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding(view)
        setOnClickListeners()
        initListeningForBoxFragmentResult() // listening for the result from BoxFragment
    }

    private fun initListeningForBoxFragmentResult() {

        val liveData = findNavController().currentBackStackEntry
            ?.savedStateHandle?.getLiveData<Int>(BoxFragment.TAG_RANDOM_NUMBER_VALUE)

        liveData?.observe(viewLifecycleOwner) { generatedNumber ->

            if (generatedNumber == null) {
                return@observe
            }

            Toast.makeText(
                requireContext(),
                "${getString(R.string.generated_number_message)} $generatedNumber",
                Toast.LENGTH_SHORT
            ).show()

            // clear data after it appeared once
            liveData.value = null

        }

    }

    private fun initBinding(view: View) {
        binding = FragmentRootBinding.bind(view)
    }

    private fun setOnClickListeners() {

        binding.btnOpenYellowBox.setOnClickListener{
            openBox(Color.rgb(255, 255, 200), getString(R.string.color_name_yellow))
        }

        binding.btnOpenGreenBox.setOnClickListener{
            openBox(Color.rgb(200, 255, 200), getString(R.string.color_name_green))
        }

    }

    private fun openBox(colorValue: Int, colorName: String) {

        // launch BoxFragment with arguments and additional options

        findNavController().navigate(

            RootFragmentDirections.actionRootFragmentToBoxFragment(colorValue, colorName),

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

}