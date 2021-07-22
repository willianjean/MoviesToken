package br.com.tokenlab.android.moviestoken.di

import android.app.Application

class MoviesApplication: Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.factory().create(this)
    }
}