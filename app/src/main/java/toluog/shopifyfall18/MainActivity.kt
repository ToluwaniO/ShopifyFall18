package toluog.shopifyfall18

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val ORDERS_TO_FULFILL = "Orders to fulfill"
    private val PENDING_PAYMENTS = "Pending Payments"
    private val CANCELLED_ORDRES = "Cancelled Orders"
    private val categories = arrayOf(Category(ORDERS_TO_FULFILL, emptyList()),
            Category(PENDING_PAYMENTS, emptyList()),
            Category(CANCELLED_ORDRES, emptyList()))
    private val orders = arrayListOf<Order>()
    private lateinit var adapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        adapter = CategoryAdapter(categories)
        category_recycler.adapter = adapter
        category_recycler.layoutManager = LinearLayoutManager(this)

        viewModel.getOrders()?.observe(this, Observer {
            it?.let {
                orders.clear()
                orders.addAll(it)
                updateView()
            }
        })
    }

    private fun updateView() {

        for (category in categories) {
            when(category.name) {
                ORDERS_TO_FULFILL -> {
                    val list = orders.filter { it.fulfillment != Fulfillment.fulfilled }.toList()
                    category.updateList(list.take(10), list.size)
                }
                PENDING_PAYMENTS -> {
                    val list = orders.filter { it.financialStatus == Finances.pending }.toList()
                    category.updateList(list.take(10), list.size)
                }
                CANCELLED_ORDRES -> {
                    val list = orders.filter { it.cancelled != null }.take(10).toList()
                    category.updateList(list.take(10), list.size)
                }
            }
            adapter.notifyParentDataSetChanged(true)
        }

    }
}
