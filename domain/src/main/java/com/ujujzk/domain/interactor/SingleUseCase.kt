package com.ujujzk.domain.interactor

import com.ujujzk.domain.executor.PostExecutionThread
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


abstract class SingleUseCase<T, in Params>
constructor(
        private val postExecutionThread: PostExecutionThread,
        private val disposables: CompositeDisposable
) {

    protected abstract fun build(params: Params): Single<T>

    open fun execute(observer: DisposableSingleObserver<T>, params: Params){
        val observable = this.build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    private fun addDisposable(disposable: Disposable) = this.disposables.add(disposable)
}