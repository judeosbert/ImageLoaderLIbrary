package com.klepto.labs.imageloader.di

import android.content.Context
import com.klepto.labs.imageloader.datasource.MemoryDataSource
import com.klepto.labs.imageloader.DataHolder
import com.klepto.labs.imageloader.Repository
import com.klepto.labs.imageloader.datasource.DataSource
import com.klepto.labs.imageloader.datasource.DiskDataSource
import com.klepto.labs.imageloader.datasource.NetworkDataSource
import com.klepto.labs.imageloader.network.ApiServiceGenerator
import com.klepto.labs.imageloader.network.CallManager
import org.koin.dsl.module

val appModule = module{
    single { ApiServiceGenerator.create() }
    single { DataHolder(get()) }
    single {CallManager()}
    single {Repository()}
    single {MemoryDataSource()}
    single {(context: Context)->DiskDataSource(context)}
    single {NetworkDataSource()}
    single {(context: Context)->DataSource(context)}

}