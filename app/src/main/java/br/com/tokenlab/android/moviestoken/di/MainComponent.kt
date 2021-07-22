package br.com.tokenlab.android.moviestoken.di

import androidx.fragment.app.Fragment
import br.com.tokenlab.android.moviestoken.MainActivity
import br.com.tokenlab.android.moviestoken.ui.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
}