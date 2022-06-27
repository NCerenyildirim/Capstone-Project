package com.example.quickup.data.main.productsbasket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.quickup.R
import com.example.quickup.databinding.FragmentProductsBasketBinding
import com.google.firebase.auth.FirebaseAuth


class ProductsBasketFragment: Fragment() {
    private var _binding: FragmentProductsBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ProductsBasketViewModel(requireContext()) }

    private val productsBasketAdapter by lazy { ProductsBasketAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_products_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseAuth.getInstance().currentUser?.let {
            viewModel.getproductsBasket(it.uid)
        }

        initObservers()

        with(binding) {

            basketRecyclerView.setHasFixedSize(true)

            productsBasketAdapter.onRemoveBasketClick = {
                FirebaseAuth.getInstance().currentUser?.let {user->
                    viewModel.deleteFromBag(it, user.uid )
                }

            }
        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {


                productsbasket.observe(viewLifecycleOwner) { list ->
                    productsBasketAdapter.updateList(list)
                    basketRecyclerView.adapter= productsBasketAdapter
                    emptyFavoritesText.visibility = View.GONE



                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
