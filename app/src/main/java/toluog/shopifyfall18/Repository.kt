package toluog.shopifyfall18

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.function.BiConsumer

class Repository() {

    private val TAG = Repository::class.java.simpleName
    private val ordersSource = OrdersDataSource()
    private val liveOrders = MutableLiveData<List<Order>>()

    fun getOrders(): LiveData<List<Order>> {
        ordersSource.getOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { orders, t2 ->
                    Log.d(TAG, "${orders.orders}")
                    Log.d(TAG, "${t2?.message}")
//                    orders.orders.forEach {
//                        if(it.fulfillment == null) {
//                            it.fulfillment = Fulfillment.non_fulfilled
//                        }
//                        if(it.cancelled == null) {
//                            it.cancelled = Cancel.not_cancelled
//                        }
//                    }
//                    Log.d(TAG, "${orders.orders}")
                    liveOrders.postValue(orders.orders)
                }
        return liveOrders
    }

}