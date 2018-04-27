package com.arctouch.codechallenge.di.component

import com.arctouch.codechallenge.di.scope.ActivityScope
import com.arctouch.codechallenge.features.home.contract.HomeActivityContract
import com.arctouch.codechallenge.features.home.presenter.HomeActivityPresenter
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface HomeActivityComponent : HomeActivityContract.Component<HomeActivityContract.View, HomeActivityPresenter> {
    override fun presenter(): HomeActivityPresenter
}