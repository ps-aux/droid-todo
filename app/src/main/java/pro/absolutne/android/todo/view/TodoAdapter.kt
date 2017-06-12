package pro.absolutne.android.todo.view

import pro.absolutne.android.framework.view.BoundAdapter
import pro.absolutne.android.todo.R
import pro.absolutne.android.todo.entity.TodoItem

class TodoAdapter(private var items: MutableList<TodoItem>,
                  private val onDelete: ((TodoItem) -> Any)? = null)

    : BoundAdapter<TodoItem>(R.layout.todo_item) {


    fun setNewItems(items: List<TodoItem>) {
        this.items = ArrayList<TodoItem>(items)
        notifyDataSetChanged()
    }

    override fun itemSwiped(index: Int) {
        onDelete?.let {
            onDelete.invoke(items[index])
        }
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun getItem(index: Int) = items[index]

    override fun getItemCount() = items.size
}