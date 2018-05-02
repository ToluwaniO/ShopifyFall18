package toluog.shopifyfall18

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class OrdersDataSource {

    private val ORDERS_URL = "https://shopicruit.myshopify.com/"
    private var ordersApi: OrdersClientAPI

    init {
        ordersApi = getOrdersService()
    }

    private fun getOrdersService(): OrdersClientAPI {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
                .baseUrl(ORDERS_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        return retrofit.create(OrdersClientAPI::class.java)
    }

    fun getOrders() = ordersApi.getOrders()

    interface OrdersClientAPI {
        @GET("admin/orders.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
        fun getOrders(): Single<Orders>
    }

    data class Orders(@SerializedName("orders") var orders: List<Order>)
}