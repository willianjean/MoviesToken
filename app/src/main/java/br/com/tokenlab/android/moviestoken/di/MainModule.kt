package br.com.tokenlab.android.moviestoken.di

import androidx.lifecycle.ViewModel
import br.com.tokenlab.android.moviestoken.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}