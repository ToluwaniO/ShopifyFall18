package toluog.shopifyfall18

import com.bignerdranch.expandablerecyclerview.model.Parent

class Category(val name: String, var orders: List<Order>, var actualSize: Int = 0) : Parent<Order> {
    override fun getChildList() = orders

    override fun isInitiallyExpanded() =false

    fun get(index: Int) = orders[index]

    fun updateList(orders: List<Order>, actualSize: Int = 0) {
        this.orders = orders
        this.actualSize = actualSize
    }

}