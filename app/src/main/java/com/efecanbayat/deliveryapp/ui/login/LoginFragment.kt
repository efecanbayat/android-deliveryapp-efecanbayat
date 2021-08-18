package com.efecanbayat.deliveryapp.ui.login

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
import com.efecanbayat.deliveryapp.ui.MainActivity
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.databinding.FragmentLoginBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        binding.registerTextView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmailEditText.editText?.text.toString()
            val password = binding.loginPasswordEditText.editText?.text.toString()
            viewModel.login(email, password)
                .observe(viewLifecycleOwner, Observer {
                    when (it.status) {
                        Resource.Status.LOADING -> {

                        }
                        Resource.Status.SUCCESS -> {
                            binding.loginCardView.visibility = View.GONE
                            binding.loginAnimation.visibility = View.VISIBLE
                            binding.loginAnimation.addAnimatorListener(object: Animator.AnimatorListener{
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
                            val dialog = AlertDialog.Builder(context)
                                .setTitle("Error")
                                .setMessage("${it.message}")
                                .setPositiveButton("ok") { dialog, button ->
                                    dialog.dismiss()
                                }
                            dialog.show()
                        }
                    }
                })
        }
    }
}