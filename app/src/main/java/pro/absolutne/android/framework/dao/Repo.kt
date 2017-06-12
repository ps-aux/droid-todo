package pro.absolutne.android.framework.dao

import io.reactivex.Flowable

interface Repo<E> {

    fun findAll(): Flowable<List<E>>

    fun insert(e: E)

    fun delete(e: E)
}