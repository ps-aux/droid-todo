package pro.absolutne.android.framework.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class BoundAdapter<T>(private val itemViewId: Int)
    : RecyclerView.Adapter<BoundViewHolder>() {

    private val touchHelper: ItemTouchHelper

    init {
        val cb = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                                  direction: Int) =
                    itemSwiped(viewHolder.adapterPosition)
        }

        touchHelper = ItemTouchHelper(cb)
    }

    open fun itemClicked(index: Int) {
    }

    open fun itemSwiped(index: Int) {
    }

    protected abstract fun getItem(index: Int): T

    override fun onAttachedToRecyclerView(v: RecyclerView) {
        touchHelper.attachToRecyclerView(v)
    }

    override fun onBindViewHolder(view: BoundViewHolder, index: Int) {
        val t = getItem(index)
        view.bind(t!!, { itemClicked(index) })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoundViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                itemViewId,
                parent,
                false)

        val holder = BoundViewHolder(binding)

        return holder
    }


}

