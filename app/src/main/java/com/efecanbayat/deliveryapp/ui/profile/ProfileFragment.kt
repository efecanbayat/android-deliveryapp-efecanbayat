package com.efecanbayat.deliveryapp.ui.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.efecanbayat.deliveryapp.R
import com.efecanbayat.deliveryapp.data.entity.order.Order
import com.efecanbayat.deliveryapp.data.entity.profile.IApplyOnClick
import com.efecanbayat.deliveryapp.databinding.FragmentProfileBinding
import com.efecanbayat.deliveryapp.ui.order.BasketItemsAdapter
import com.efecanbayat.deliveryapp.ui.splash.SplashActivity
import com.efecanbayat.deliveryapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class ProfileFragment: Fragment(){
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val orderAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){

        getUser()

        getOrders()

        binding.profileUpdateTextView.setOnClickListener {
            //show dialog to update profile
            val dialog = ProfileDialogFragment()
            dialog.show(requireActivity().supportFragmentManager,"profileDialog")
        }

        binding.logoutButton.setOnClickListener {
            logout()
        }
    }

    private fun getOrders() {
        viewModel.getOrders().observe(viewLifecycleOwner, {
            when(it.status){
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

                }
            }
        })
    }


    private fun getUser() {
        viewModel.getUser().observe(viewLifecycleOwner, {
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.profileProgressBar.visibility = View.VISIBLE
                    binding.profileCardConstraint.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {

                    binding.profileProgressBar.visibility = View.GONE
                    binding.profileCardConstraint.visibility = View.VISIBLE

                    binding.profileImageView.setImageResource(R.drawable.user)
                    binding.profileNameTextView.text = it.data!!.user.name
                    binding.profileMailTextView.text = it.data.user.email
                    binding.profilePhoneTextView.text = it.data.user.phone
                    binding.profileAddressTextView.text = it.data.user.address
                }
                Resource.Status.ERROR -> {

                }
            }
        })
    }

    private fun logout() {
        viewModel.logout()
        val intent = Intent(context, SplashActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

}