package pro.absolutne.android.framework.service

import pro.absolutne.android.framework.RxUtils
import pro.absolutne.android.framework.dao.Repo

abstract class DaoService<E>(val dao: Repo<E>) {

    fun findAll(onNext: (List<E>) -> Unit,
                onError: (Throwable) -> Unit = {},
                onDone: () -> Unit = {}) =
            RxUtils.asyncOperation(dao.findAll(), onNext, onError, onDone)!!

    fun insert(e: E, success: () -> Unit = {}, error: (Throwable) -> Unit = {}) =
            RxUtils.asyncOperation({ dao.insert(e) }, success, error)

    fun delete(e: E, success: () -> Unit = {}, error: (Throwable) -> Unit = {}) {
        RxUtils.asyncOperation({ dao.delete(e) }, success, error)
    }
}