package com.renarosantos.ecommerceapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.renarosantos.ecommerceapp.domain.ProductRepositoryAPI
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {

    private var productId = 0

    private val repository = ProductRepositoryAPI()

    private val _viewState = MutableLiveData<ProductDetailsViewState>()
    val viewState: LiveData<ProductDetailsViewState>
        get() = _viewState

    fun setProductId(productId: Int) {
        this.productId = productId
    }

    fun loadProduct() {
        _viewState.postValue(ProductDetailsViewState.Loading)
        // Data call to fetch products
        viewModelScope.launch {
            val product = repository.getProduct(productId)
            _viewState.postValue(ProductDetailsViewState.Content(product))
        }
    }
}