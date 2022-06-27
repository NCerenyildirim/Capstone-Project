package com.example.quickup.data.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quickup.R
import com.example.quickup.databinding.FragmentNewProductsBinding
import com.google.firebase.auth.FirebaseAuth


class NewProductsFragment : Fragment() {


    private lateinit var binding: FragmentNewProductsBinding

    private val productsAdapter by lazy { ProductsAdapter() }

    private val viewModel by lazy { NewProductsViewModel(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_new_products, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        productsAdapter.onFavoritesClick = { viewModel.addToFavorites(it) }
        productsAdapter.onBasketClick = {
            FirebaseAuth.getInstance().currentUser?.let { user ->
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
        with(binding) {
            with(viewModel) {
                productsList.observe(viewLifecycleOwner) { list ->
                    newproductsrecyler.apply {
                        adapter = productsAdapter.also {
                            it.updateProductsList(list)
                        }

                    }
                }
            }
        }
    }
}