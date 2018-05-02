package toluog.shopifyfall18

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter

class CategoryAdapter(private val categories: Array<Category>): ExpandableRecyclerAdapter<Category, Order,
        CategoryViewHolder, OrderViewHolder>(categories.toList()) {
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): CategoryViewHolder {
        val v = LayoutInflater.from(parentViewGroup.context).inflate(R.layout.category_layout,
                parentViewGroup, false)
        return CategoryViewHolder(v)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): OrderViewHolder {
        val v = LayoutInflater.from(childViewGroup.context).inflate(R.layout.order_layout,
                childViewGroup, false)
        return OrderViewHolder(v)
    }

    override fun onBindChildViewHolder(childViewHolder: OrderViewHolder, parentPosition: Int, childPosition: Int, child: Order) {
        childViewHolder.bind(categories[parentPosition].get(childPosition))
    }

    override fun onBindParentViewHolder(parentViewHolder: CategoryViewHolder, parentPosition: Int, parent: Category) {
        parentViewHolder.bind(categories[parentPosition])
    }


}