package pro.absolutne.android.todo.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@android.arch.persistence.room.Entity(tableName = "todo_item")
data class TodoItem(var text: String) {

    @android.arch.persistence.room.PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
