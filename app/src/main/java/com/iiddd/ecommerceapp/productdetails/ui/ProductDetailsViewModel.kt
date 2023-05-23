package com.iiddd.ecommerceapp.productdetails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiddd.ecommerceapp.shared.data.repository.api.ProductRepositoryAPI
import com.iiddd.ecommerceapp.productdetails.ui.viewstate.ProductDetailsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel
@Inject constructor(private val repository: ProductRepositoryAPI) : ViewModel() {

    private val _viewState = MutableLiveData<ProductDetailsViewState>()
    val viewState: LiveData<ProductDetailsViewState>
        get() = _viewState

    fun loadProduct(productId: String) {
        _viewState.postValue(ProductDetailsViewState.Loading)
        // Data call to fetch products
        viewModelScope.launch {
            val productDetails = repository.getProductDetails(productId)
            _viewState.postValue(ProductDetailsViewState.Content(productDetails))
        }
    }
}