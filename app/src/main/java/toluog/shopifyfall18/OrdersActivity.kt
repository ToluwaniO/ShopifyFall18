package toluog.shopifyfall18

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : AppCompatActivity() {

    private lateinit var orders: List<Order>
    private lateinit var adapter: OrdersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        orders = intent.extras[AppContract.ORDERS_BUNDLE] as List<Order>
        adapter = OrdersAdapter(orders)
        orders_recycler.adapter = adapter
        orders_recycler.layoutManager = LinearLayoutManager(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if (id == android.R.id.home) onBackPressed()
        return true
    }
}
