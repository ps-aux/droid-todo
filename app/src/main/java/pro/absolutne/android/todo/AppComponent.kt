package pro.absolutne.android.todo

@javax.inject.Singleton
@dagger.Component(modules = arrayOf(pro.absolutne.android.todo.AppModule::class))
interface AppComponent {

    fun inject(activity: pro.absolutne.android.todo.MainActivity)
}
