package com.iiddd.ecommerceapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiddd.ecommerceapp.domain.ProductRepositoryAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel
@Inject constructor(private val repository: ProductRepositoryAPI) : ViewModel() {

    private var productId = 0

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