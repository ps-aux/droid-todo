package pro.absolutne.android.todo

object Injectors {

    lateinit private var appModule: AppComponent

    fun setAppInjector(inj: AppComponent) {
        Injectors.appModule = inj
    }

    fun getAppInjector() = Injectors.appModule

}