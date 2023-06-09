package com.iiddd.ecommerceapp.productlist.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iiddd.ecommerceapp.databinding.FragmentProductsBinding
import com.iiddd.ecommerceapp.productlist.ui.ProductListViewModel
import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductCardViewState
import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductListViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var adapter: ProductCardListAdapter
    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }

    private fun onViewCreated() {
        adapter = ProductCardListAdapter(::navigateToProduct, ::onFavoriteIconClicked)
        binding.viewProductList.layoutManager =
            LinearLayoutManager(requireContext())
        binding.viewProductList.adapter = adapter
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            updateUI(viewState)
        }
        viewModel.loadProductList()
    }

    private fun updateUI(viewState: ProductListViewState) {
        when (viewState) {
            is ProductListViewState.Content -> {
                with(binding) {
                    viewProductList.isVisible = true
                    errorView.isVisible = false
                    errorTextView.isVisible = false
                    loadingView.isVisible = false

                }
                adapter.setData(viewState.productList)
            }

            is ProductListViewState.Error -> {
                with(binding) {
                    viewProductList.isVisible = false
                    errorView.isVisible = true
                    loadingView.isVisible = false
                    errorTextView.isVisible = true
                    errorTextView.text = viewState.errorMessage
                }
            }

            ProductListViewState.Loading -> {
                with(binding) {
                    viewProductList.isVisible = false
                    errorView.isVisible = false
                    errorTextView.isVisible = false
                    loadingView.isVisible = true
                }
            }
        }
    }

    private fun navigateToProduct(viewState: ProductCardViewState) {
        val direction = ProductListFragmentDirections.goToProduct()
        findNavController().navigate(direction)
    }

    private fun onFavoriteIconClicked(viewState: ProductCardViewState) {
        viewModel.favoriteIconClicked(viewState.id)
    }
}