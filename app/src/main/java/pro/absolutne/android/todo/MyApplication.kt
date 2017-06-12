package pro.absolutne.android.todo

import android.util.Log
import com.facebook.stetho.Stetho
import timber.log.Timber

class MyApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)

        val appModule = DaggerAppComponent.builder()
                .appModule(pro.absolutne.android.todo.AppModule(this))
                .build()

        Injectors.setAppInjector(appModule)

        Timber.plant(Timber.DebugTree())

        Log.d("app", "in my application........")
    }
}
