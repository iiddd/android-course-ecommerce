package com.iiddd.ecommerceapp.productlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductCardViewState
import com.iiddd.ecommerceapp.productlist.ui.viewstate.ProductListViewState
import com.iiddd.ecommerceapp.shared.domain.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductRepository,
//    private val wishlistRepository: WishlistRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ProductListViewState>()
    val viewState: LiveData<ProductListViewState>
        get() = _viewState


    fun loadProductList() {
        viewModelScope.launch {
            _viewState.postValue(ProductListViewState.Loading)
            // Data call to fetch products
            val productList = repository.getProductList()
            _viewState.postValue(
                ProductListViewState.Content(
                    productList.map {
                        ProductCardViewState(
                            it.title,
                            it.description,
                            "US $ ${it.price}",
                            it.imageUrl,
                            isFavorite = false
                        )
                    }
                ))
        }
    }
}