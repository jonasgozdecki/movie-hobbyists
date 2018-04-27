package com.arctouch.codechallenge.base

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>,
        C : BaseContract.Component<V, P>> : Fragment(), BaseContract.View {

    protected val presenter: P by lazy { component.presenter() }
    protected val component: C by lazy { createComponent() }

    protected abstract fun createComponent(): C

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this as V)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
