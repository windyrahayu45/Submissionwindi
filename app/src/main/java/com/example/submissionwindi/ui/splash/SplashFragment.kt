package com.example.submissionwindi.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.submissionwindi.R
import com.example.submissionwindi.databinding.FragmentSplashBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val binding by viewBinding<FragmentSplashBinding>()
    private val viewModel by viewModel<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initObservable()
    }

    private fun initBinding() = with(binding) {
        imgViewLogin.load(R.drawable.logo)
    }

    private fun initObservable() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    val mHandler = Handler()
                    val monitor = Runnable {
                        if (it.isLogin) {
                            findNavController().navigate(
                                R.id.action_splashFragment_to_listFragment
                            )
                        } else {
                            findNavController().navigate(
                                R.id.action_splashFragment_to_loginFragment
                            )
                        }
                    }
                    //runnable

                    mHandler.postDelayed(monitor, 3_000)
                }
            }
        }
    }
}