package com.example.quickup.data.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quickup.R
import com.example.quickup.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding


    private val discountAdapter by lazy { DiscountAdapter() }

    private val viewModel by lazy { HomeFragmentViewModel(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        discountAdapter.onFavoritesClick = {viewModel.addToFavorites(it)}
        discountAdapter.onBasketClick = {
            FirebaseAuth.getInstance().currentUser?.let {user->
                viewModel.addToBag(
                    user.uid,
                    it.title,
                    it.price,
                    it.description,
                    it.category,
                    it.image,
                    it.rate,
                    it.count,
                    it.saleState
                )
            }





        }
    }


    private fun initObservers() {
        with(binding){
            with(viewModel){
                discountedList.observe(viewLifecycleOwner){ items ->
                    DiscountRecycler.apply {
                        adapter=discountAdapter.also {
                            it.updateProductsList(items)
                        }
                    }
                }
            }
        }


    }


    }
