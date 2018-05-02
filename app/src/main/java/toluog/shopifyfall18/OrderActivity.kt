package toluog.shopifyfall18

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.item_layout.*

class OrderActivity : AppCompatActivity() {

    private lateinit var order: Order
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        order = intent.extras[AppContract.ORDER_BUNDLE] as Order
        updateView()
        adapter = ItemAdapter(order.items)
        item_recycler.adapter = adapter
        item_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) onBackPressed()
        return true
    }

    private fun updateView() {
        order_name.text = order.name
        items_number.text = order.items.size.toString()
        payment_status.text = paymentStatus(order.financialStatus)
        fulfillment_status.text = fulfillmentStatus(order.fulfillment)
        cancellation_status.text = cancellationStatus(order.cancelled)
    }

    private fun paymentStatus(finances: Finances): String {
        return when(finances) {
            Finances.paid -> "Paid"
            Finances.pending -> "Pending"
            Finances.authorized -> "Authorized"
            Finances.partially_refunded, Finances.refunded -> "Refunded"
            Finances.voided -> "Voided"
            else -> "Pending"
        }
    }

    private fun fulfillmentStatus(fulfillment: Fulfillment?): String {
        return when(fulfillment) {
            Fulfillment.fulfilled -> "Fulfilled"
            Fulfillment.restocked -> "Restocked"
            else -> "Unfulfilled"
        }
    }

    private fun cancellationStatus(cancel: Cancel?): String {
        return when(cancel) {
            null -> "Not cancelled"
            else -> "cancelled"
        }
    }

    class ItemAdapter(private val items: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(items[position])
        }

        class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

            fun bind(item: Item) {
                item_name.text = item.name
                item_price.text = containerView.context.getString(R.string.price, item.price)
                val itemsNumber = when {
                    item.quantity == 0 -> containerView.context.getString(R.string.zero_items)
                    item.quantity == 1 -> containerView.context.getString(R.string.items_number_one)
                    else -> containerView.context.getString(R.string.items_number, item.quantity)
                }
                item_quantity.text = itemsNumber
            }

        }

    }
}
