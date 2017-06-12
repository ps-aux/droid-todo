package pro.absolutne.android.todo.persistence.db.dao


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

import pro.absolutne.android.todo.entity.TodoItem
import pro.absolutne.android.framework.dao.Repo

@Dao
interface TodoItemDao : Repo<TodoItem> {

    @Query("SELECT * FROM todo_item")
    override fun findAll(): Flowable<List<TodoItem>>

    @Delete
    override fun delete(e: TodoItem)

    @Insert
    override fun insert(e: TodoItem)
}
