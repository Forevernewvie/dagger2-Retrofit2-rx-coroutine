package com.example.ktrotest.data.dailyBoxOffice.di.networkmodule

import com.example.ktrotest.data.dailyBoxOffice.remote.DailyBoxOfficeRemoteDataSourceImpl
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun inject(dailyBoxOfficeRemoteDataSourceImpl: DailyBoxOfficeRemoteDataSourceImpl)

}