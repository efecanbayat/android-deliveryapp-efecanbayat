package com.efecanbayat.deliveryapp.ui.profile

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.databinding.FragmentProfileDialogBinding
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDialogFragment: DialogFragment(){
    private lateinit var binding: FragmentProfileDialogBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDialogBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        viewModel.getUser().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.dialogProgressBar.visibility = View.VISIBLE
                    binding.itemConstraintLayout.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {

                    binding.dialogProgressBar.visibility = View.GONE
                    binding.itemConstraintLayout.visibility = View.VISIBLE

                    binding.updateNameEditText.editText?.setText(it.data!!.user.name)
                    binding.updateMailEditText.editText?.setText(it.data!!.user.email)
                    binding.updatePhoneEditText.editText?.setText(it.data!!.user.phone)
                    binding.updateAddressEditText.editText?.setText(it.data!!.user.address)
                }
                Resource.Status.ERROR -> {

                }
            }
        })

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.applyButton.setOnClickListener {
            val name = binding.updateNameEditText.editText?.text.toString()
            val email = binding.updateMailEditText.editText?.text.toString()
            val phone = binding.updatePhoneEditText.editText?.text.toString()
            val address = binding.updateAddressEditText.editText?.text.toString()

            viewModel.updateUser(UserRequest(name,email,phone,address)).observe(viewLifecycleOwner, {
                when(it.status){
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.SUCCESS -> {
                        dismiss()
                        findNavController().navigate(R.id.action_profileFragment_self)
                    }
                    Resource.Status.ERROR -> {

                    }
                }
            })
        }
    }

}