package com.iiddd.ecommerceapp.productdetails.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.iiddd.ecommerceapp.databinding.FragmentProductDetailsBinding
import com.iiddd.ecommerceapp.productdetails.ui.ProductDetailsViewModel
import com.iiddd.ecommerceapp.productdetails.ui.viewstate.ProductDetailsViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private val viewModel: ProductDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadProduct("someProductId")
        viewModel.viewState.observe(viewLifecycleOwner) {
            updateUi(it)
        }
    }

    private fun updateUi(viewState: ProductDetailsViewState) {
        when (viewState) {
            is ProductDetailsViewState.Content -> {
                with(binding) {
                    errorView.isVisible = false
                    loadingView.isVisible = false
                    errorTextView.isVisible = false
                    errorTextView.isVisible = false
                }
                showProductViews()
                setProduct(viewState)
            }

            is ProductDetailsViewState.Loading -> {
                hideProductViews()
                with(binding) {
                    errorView.isVisible = false
                    errorTextView.isVisible = false
                    loadingView.isVisible = true
                }
            }

            is ProductDetailsViewState.Error -> {
                hideProductViews()
                with(binding) {
                    errorView.isVisible = true
                    loadingView.isVisible = false
                    errorTextView.isVisible = true
                    errorTextView.text = viewState.errorMessage
                }
            }
        }
    }

    private fun hideProductViews() {
        with(binding) {
            viewProductName.isVisible = false
            viewProductDescription.isVisible = false
            productPrice.isVisible = false
            productImage.isVisible = false
            buyButton.isVisible = false
        }
    }

    private fun showProductViews() {
        with(binding) {
            viewProductName.isVisible = true
            viewProductDescription.isVisible = true
            productPrice.isVisible = true
            productImage.isVisible = true
            buyButton.isVisible = true
        }
    }

    private fun setProduct(viewState: ProductDetailsViewState.Content) {
        with(binding) {
            viewProductName.text = viewState.product.title
            viewProductDescription.text = viewState.product.description
            productPrice.text = viewState.product.price
            Glide.with(requireContext())
                .load(viewState.product.imageUrl)
                .into(productImage)
        }
    }
}

