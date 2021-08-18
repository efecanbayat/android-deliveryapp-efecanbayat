package com.efecanbayat.deliveryapp.ui.register

import android.animation.Animator
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.databinding.FragmentRegisterBinding
import com.efecanbayat.deliveryapp.ui.MainActivity
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {



        binding.signInTextView.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.registerButton.setOnClickListener {

            val name = binding.registerNameEditText.editText?.text.toString()
            val email = binding.registerEmailEditText.editText?.text.toString()
            val password = binding.registerPasswordEditText.editText?.text.toString()
            val confirmPassword = binding.registerConfirmPasswordEditText.editText?.text.toString()

            if (password == confirmPassword) {
                binding.registerCardView.visibility = View.GONE
                binding.registerAnimation.visibility = View.VISIBLE
                binding.registerAnimation.playAnimation()
                register(name,email,password)

            } else {
                val dialog = AlertDialog.Builder(context)
                    .setTitle("Password Error")
                    .setMessage("Please retype your password correctly")
                    .setPositiveButton("ok") { dialog, button ->
                        dialog.dismiss()
                    }
                dialog.show()
            }
        }

    }

    private fun register(name: String, email: String, password: String) {
        viewModel.register(name, email, password).observe(viewLifecycleOwner, Observer {
            when(it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {

                    binding.registerAnimation.setAnimation(R.raw.success)
                    binding.registerAnimation.playAnimation()
                    binding.registerAnimation.addAnimatorListener(object: Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                            Log.v("Animation", "Started")
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            val intent = Intent(context, MainActivity::class.java)
                            startActivity(intent)
                            requireActivity().finish()
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            Log.v("Animation", "Canceled")
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                            Log.v("Animation", "Repeated")
                        }

                    })
                }
                Resource.Status.ERROR -> {

                    binding.registerAnimation.setAnimation(R.raw.fail)
                    binding.registerAnimation.playAnimation()
                    binding.registerAnimation.addAnimatorListener(object: Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {
                            Log.v("Animation", "Started")
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            binding.registerAnimation.visibility = View.GONE
                            binding.registerCardView.visibility = View.VISIBLE
                            clearEditTexts()
                            binding.registerAnimation.setAnimation(R.raw.loading)
                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("ok") { dialog, button ->
                                    dialog.dismiss()
                                    binding.registerAnimation.removeAllAnimatorListeners()
                                }
                            dialog.show()
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                            Log.v("Animation", "Canceled")
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                            Log.v("Animation", "Repeated")
                        }

                    })
                }
            }
        })
    }

    private fun clearEditTexts() {
        binding.registerNameEditText.editText?.text?.clear()
        binding.registerEmailEditText.editText?.text?.clear()
        binding.registerPasswordEditText.editText?.text?.clear()
        binding.registerConfirmPasswordEditText.editText?.text?.clear()
    }
}