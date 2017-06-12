package pro.absolutne.android.todo

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UiSupport @Inject constructor() {

    fun createToast(a: Activity, text: String) {

        val layout = a.layoutInflater.inflate(R.layout.toast, null)

        val tv = layout.findViewById(R.id.toast_text) as TextView

        tv.text = text

        val toast = Toast(a)

        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.duration = Toast.LENGTH_LONG
        toast.view = tv
        toast.show()
    }
}