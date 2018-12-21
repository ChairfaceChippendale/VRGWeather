package com.ujujzk.networking.di

import dagger.Component

@Component(dependencies = [], modules = [NetworkModule::class])
interface NetworkComponent {
 fun provideNetwork
}