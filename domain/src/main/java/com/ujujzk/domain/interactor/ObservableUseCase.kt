package com.ujujzk.domain.interactor

import com.ujujzk.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


abstract class ObservableUseCase<T, in Params>
constructor(
        private val postExecutionThread: PostExecutionThread,
        private val disposables: CompositeDisposable
) {

    protected abstract fun build(params: Params): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params){
        val observable = this.build(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() = disposables.dispose()

    private fun addDisposable(disposable: Disposable) = this.disposables.add(disposable)
}