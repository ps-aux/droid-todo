package pro.absolutne.android.framework.view

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import pro.absolutne.android.todo.BR

class BoundViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(o: Any, clickCb: (View) -> Unit) {
        binding.setVariable(BR.obj, o)
        binding.executePendingBindings()
        itemView.setOnLongClickListener({
            clickCb(it)
            true
        })
    }

}