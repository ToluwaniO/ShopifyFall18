package toluog.shopifyfall18

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private val repo = Repository()
    private var orders: LiveData<List<Order>>? = null

    init {
        orders = repo.getOrders()
    }

    fun getOrders() = orders
}