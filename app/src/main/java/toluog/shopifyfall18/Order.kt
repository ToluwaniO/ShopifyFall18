package toluog.shopifyfall18

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

enum class Fulfillment {
    fulfilled, partial, restocked, non_fulfilled
}
enum class Finances {
    pending, authorized, partially_paid, paid, partially_refunded, refunded, voided
}
enum class Cancel {
    customer, fraud, inventory, declined
}

@Parcelize
data class Order (@SerializedName("id") var id: Long = 0L,
                  @SerializedName("name") var name: String = "#0",
                  @SerializedName("line_items") var items: List<Item>,
                  @SerializedName("total_price") var totalPrice: Double = 0.0,
                  @SerializedName("financial_status") var financialStatus: Finances = Finances.pending,
                  @SerializedName("fulfillment_status") var fulfillment: Fulfillment? = null,
                  @SerializedName("cancel_reason") var cancelled: Cancel? = null): Parcelable

@Parcelize
data class Item(@SerializedName("id") var id: Long = 0L,
                @SerializedName("title") var name: String = "",
                @SerializedName("quantity") var quantity: Int = 0,
                @SerializedName("price") var price: Double = 0.0): Parcelable