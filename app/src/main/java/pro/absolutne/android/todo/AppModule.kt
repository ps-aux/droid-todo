package pro.absolutne.android.todo

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import pro.absolutne.android.todo.persistence.db.AppDatabase
import pro.absolutne.android.todo.persistence.db.dao.TodoItemDao
import javax.inject.Singleton

@Module
class AppModule(private val app: android.app.Application) {

    @Provides
    @Singleton
    fun provideContext(): android.content.Context = app

    @Provides
    @Singleton
    fun database(): AppDatabase = Room
            .databaseBuilder(
                    app.applicationContext,
                    AppDatabase::class.java,
                    "todo-databaaza")
            .build()

    @Provides
    @Singleton
    fun todoItemDao(db: AppDatabase): TodoItemDao = db.todoItemDao()

}
