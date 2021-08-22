package com.efecanbayat.deliveryapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.order.Order
import com.efecanbayat.deliveryapp.databinding.FragmentProfileBinding
import com.efecanbayat.deliveryapp.ui.splash.SplashActivity
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val orderAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUser()
        getOrders()
        addListener()
    }

    private fun getUser() {
        binding.apply {

            viewModel.getUser().observe(viewLifecycleOwner, {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        profileCardConstraint.visibility = View.GONE
                    }
                    Resource.Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        profileCardConstraint.visibility = View.VISIBLE
                        profileImageView.setImageResource(R.drawable.user)
                        profileNameTextView.text = it.data!!.user.name
                        profileMailTextView.text = it.data.user.email
                        profilePhoneTextView.text = it.data.user.phone
                        profileAddressTextView.text = it.data.user.address
                    }
                    Resource.Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun getOrders() {
        viewModel.getOrders().observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {

                }
                Resource.Status.SUCCESS -> {
                    it.data?.orderList?.let {
                        binding.ordersRecylerView.layoutManager = LinearLayoutManager(context)
                        binding.ordersRecylerView.adapter = orderAdapter
                        orderAdapter.setOrderList(it)
                    }
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(requireContext(), "Error! Try again", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun addListener() {

        orderAdapter.addListener(object : IOrderOnClickListener {
            override fun onClick(order: Order) {
                val dialog = OrderDialogFragment(order.meals)
                dialog.show(requireActivity().supportFragmentManager, "orderDetailDialog")
            }

        })

        binding.editProfileImageView.setOnClickListener {
            //show dialog to update profile
            val dialog = ProfileDialogFragment()
            dialog.show(requireActivity().supportFragmentManager, "profileDialog")
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        viewModel.logout()
        val intent = Intent(context, SplashActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}