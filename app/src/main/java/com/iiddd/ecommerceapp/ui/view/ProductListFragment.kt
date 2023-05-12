package com.iiddd.ecommerceapp.ui.view

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
import com.iiddd.ecommerceapp.ui.ProductListFragmentDirections
import com.iiddd.ecommerceapp.ui.ProductListViewModel
import com.iiddd.ecommerceapp.ui.viewstate.ProductListViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var adapter: ProductCardListAdapter
    private lateinit var binding: FragmentProductsBinding
    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRecyclerView()
    }

    private fun bindRecyclerView() {
        adapter = ProductCardListAdapter(::navigateToProduct)
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
                    loadingView.isVisible = false
                    errorTextView.isVisible = false

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

    private fun navigateToProduct(itemId: Int) {
        val direction = ProductListFragmentDirections.goToProduct(itemId)
        findNavController().navigate(direction)
    }
}