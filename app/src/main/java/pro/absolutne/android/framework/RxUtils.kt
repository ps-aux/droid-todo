package pro.absolutne.android.framework

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toCompletable
import io.reactivex.schedulers.Schedulers


object RxUtils {

    fun <T> asyncOperation(o: Observable<T>,
                           success: (T) -> Unit,
                           error: (Throwable) -> Unit) =
            o.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success, error)

    fun <T> asyncOperation(f: Flowable<T>,
                           onNext: (T) -> Unit,
                           onError: (Throwable) -> Unit,
                           onDone: () -> Unit) =
            f.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onNext, onError, onDone)

    fun asyncOperation(action: () -> Unit,
                       success: () -> Unit,
                       error: (Throwable) -> Any) =
            action.toCompletable()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(success, onError(error))

    private fun onError(handler: (Throwable) -> Any) =
            { t: Throwable ->
                t.printStackTrace()
                handler(t)
                Unit
            }
}
