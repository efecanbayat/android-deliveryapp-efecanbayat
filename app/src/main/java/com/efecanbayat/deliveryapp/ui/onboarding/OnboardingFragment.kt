package com.efecanbayat.deliveryapp.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.local.SharedPrefManager
import com.efecanbayat.deliveryapp.databinding.FragmentOnboardingBinding

class OnboardingFragment: Fragment() {
    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        val adapter = OnboardingAdapter(requireActivity())
        binding.onboardingViewPager.adapter = adapter
        binding.onboardingViewPager.setPageTransformer(ZoomOutTransformation())
        binding.dotsIndicator.setViewPager2(binding.onboardingViewPager)

        binding.onboardingViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding.prevButton.visibility = View.GONE
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem = binding.onboardingViewPager.currentItem + 1
                    }
                } else if (position == 2) {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = "Finish"
                    binding.nextButton.setOnClickListener {
                        SharedPrefManager(requireContext()).setOnboardingSeen()
                        findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)

                    }
                } else {
                    binding.prevButton.visibility = View.VISIBLE
                    binding.nextButton.text = "Next"
                    binding.nextButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem + 1
                    }
                    binding.prevButton.setOnClickListener {
                        binding.onboardingViewPager.currentItem =
                            binding.onboardingViewPager.currentItem - 1
                    }
                }
            }
        })
    }
}