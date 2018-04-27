package com.arctouch.codechallenge.di.component

import com.arctouch.codechallenge.di.scope.ActivityScope
import com.arctouch.codechallenge.features.home.contract.DetailsActivityContract
import com.arctouch.codechallenge.features.home.presenter.DetailsActivityPresenter
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface DetailsActivityComponent : DetailsActivityContract.Component<DetailsActivityContract.View, DetailsActivityPresenter> {
    override fun presenter(): DetailsActivityPresenter
}