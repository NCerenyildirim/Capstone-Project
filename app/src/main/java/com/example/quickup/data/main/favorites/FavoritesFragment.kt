package com.example.quickup.data.main.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.quickup.R
import com.example.quickup.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { FavoritesViewModel(requireContext()) }

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(binding) {

            favoritesRecyclerView.setHasFixedSize(true)

            favoritesAdapter.onRemoveFavoritesClick = {
                viewModel.deleteFavoritesFromBasket(it)
            }
        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {


                productsfavorites.observe(viewLifecycleOwner) { list ->
                    favoritesAdapter.updateList(list)
                    favoritesRecyclerAdapter = favoritesAdapter
                    emptyFavoritesText.visibility = View.GONE


                    if (list.isNotEmpty()) {
                        goToBasketButton.setOnClickListener {
                            it.findNavController()
                                .navigate(R.id.action_favoritesFragment2_to_productsBasketFragment2)
                        }
                    }
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
