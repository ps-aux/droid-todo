package pro.absolutne.android.todo.service

import pro.absolutne.android.todo.entity.TodoItem
import pro.absolutne.android.framework.service.DaoService
import pro.absolutne.android.todo.persistence.db.dao.TodoItemDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TodoItemService @Inject
constructor(dao: TodoItemDao) : DaoService<TodoItem>(dao)
