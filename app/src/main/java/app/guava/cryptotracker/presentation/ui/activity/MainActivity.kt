package app.guava.cryptotracker.presentation.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.guava.cryptotracker.R
import app.guava.cryptotracker.databinding.ActivityMainBinding
import app.guava.cryptotracker.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): MainViewModel {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        return viewModel
    }

}