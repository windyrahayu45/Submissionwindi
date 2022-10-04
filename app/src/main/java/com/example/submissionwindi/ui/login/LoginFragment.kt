package com.example.submissionwindi.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.submissionwindi.R
import com.example.submissionwindi.databinding.FragmentLoginBinding
import com.example.submissionwindi.utils.safeNavigate
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // On Back
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initObservable()
    }

    private fun initBinding() = with(binding) {
        imgViewLogin.load(R.drawable.login)

        etEmail.addTextChangedListener {
            // Cek Error Email
        }

        etPassword.addTextChangedListener {
            // Cek Error Password
        }

        btnLogin.setOnClickListener {
            viewModel.login(
                email = etEmail.text.toString(),
                password = etPassword.text.toString()
            )
        }

        txtActionRegister.setOnClickListener {
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }
    }

    private fun initObservable() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.loading) {
                        binding.btnLogin.visibility = View.GONE
                        binding.progressCircular.visibility = View.VISIBLE

                    } else {
                        binding.btnLogin.visibility = View.VISIBLE
                        binding.progressCircular.visibility = View.GONE
                    }

                    if (it.loginSuccess) {
                        findNavController().safeNavigate(
                            currentDestinationId = findNavController().currentDestination?.id,
                            destinationId = R.id.loginFragment
                        )
                    }

                    if (it.error.isNotEmpty()) {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}