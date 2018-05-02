package toluog.shopifyfall18

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bignerdranch.expandablerecyclerview.ChildViewHolder
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.bignerdranch.expandablerecyclerview.model.Parent
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.category_layout.*
import kotlinx.android.synthetic.main.order_layout.*

class CategoryViewHolder(override val containerView: View) : ParentViewHolder<Parent<Category>, Category>(containerView), LayoutContainer {

    private val TAG = CategoryViewHolder::class.java.simpleName

    fun bind(category: Category) {
        val numberTitle: String = if(category.actualSize == 0) {
            containerView.context.getString(R.string.zero_items)
        } else if(category.actualSize == 1) {
            containerView.context.getString(R.string.items_number_one)
        } else {
            containerView.context.getString(R.string.items_number, category.actualSize)
        }
        category_name.text = category.name
        orders_number.text = numberTitle
        containerView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, OrdersActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putParcelableArrayList(AppContract.ORDERS_BUNDLE, ArrayList(category.orders))
                })
            }
            context.startActivity(intent)
        }
        dropdown.setOnClickListener {
            Log.d(TAG, "Dropdown clicked")
            if(isExpanded) {
                dropdown.setImageResource(R.drawable.ic_arrow_down)
                collapseView()
            }
            else {
                dropdown.setImageResource(R.drawable.ic_arrow_up)
                expandView()
            }
        }
    }

}

class OrderViewHolder(override val containerView: View): ChildViewHolder<View>(containerView), LayoutContainer {

    fun bind(order: Order) {
        order_name.text = order.name
        order_price.text = containerView.context.getString(R.string.price, order.totalPrice)

        containerView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, OrderActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putParcelable(AppContract.ORDER_BUNDLE, order)
                })
            }
            context.startActivity(intent)
        }
    }

}