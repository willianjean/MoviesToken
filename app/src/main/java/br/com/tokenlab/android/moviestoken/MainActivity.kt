package br.com.tokenlab.android.moviestoken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.tokenlab.android.moviestoken.di.MainComponent
import br.com.tokenlab.android.moviestoken.di.MoviesApplication

class MainActivity : AppCompatActivity() {

    lateinit var mainComponent: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as MoviesApplication).appComponent.mainComponent().create()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}