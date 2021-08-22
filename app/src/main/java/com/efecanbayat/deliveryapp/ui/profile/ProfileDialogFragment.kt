package com.efecanbayat.deliveryapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.profile.UserRequest
import com.efecanbayat.deliveryapp.databinding.FragmentProfileDialogBinding
import com.efecanbayat.deliveryapp.utils.Resource
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentProfileDialogBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUsersInformation()
        addListeners()
    }

    private fun initUsersInformation() {

        binding.apply {

            viewModel.getUser().observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        dialogProgressBar.visibility = View.VISIBLE
                        itemConstraintLayout.visibility = View.GONE
                    }
                    Resource.Status.SUCCESS -> {
                        dialogProgressBar.visibility = View.GONE
                        itemConstraintLayout.visibility = View.VISIBLE
                        updateNameEditText.editText?.setText(it.data!!.user.name)
                        updateMailEditText.editText?.setText(it.data!!.user.email)
                        updatePhoneEditText.editText?.setText(it.data!!.user.phone)
                        updateAddressEditText.editText?.setText(it.data!!.user.address)
                    }
                    Resource.Status.ERROR -> {
                        dialogProgressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }

    }

    private fun addListeners() {

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.applyButton.setOnClickListener {

            binding.apply {

                val name = updateNameEditText.editText?.text.toString()
                val email = updateMailEditText.editText?.text.toString()
                val phone = updatePhoneEditText.editText?.text.toString()
                val address = updateAddressEditText.editText?.text.toString()

                viewModel.updateUser(UserRequest(name, email, phone, address))
                    .observe(viewLifecycleOwner, {
                        when (it.status) {
                            Resource.Status.LOADING -> {

                            }
                            Resource.Status.SUCCESS -> {
                                dismiss()
                                findNavController().navigate(R.id.action_profileFragment_self)
                            }
                            Resource.Status.ERROR -> {
                                Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
            }
        }
    }
}