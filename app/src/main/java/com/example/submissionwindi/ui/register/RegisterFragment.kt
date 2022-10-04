package com.example.submissionwindi.ui.register

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.submissionwindi.R
import com.example.submissionwindi.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding by viewBinding<FragmentRegisterBinding>()
    private val viewModel by viewModel<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // On Back
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBinding()
        initObservable()
    }

    private fun initBinding() = with(binding) {
        imgViewLogin.load(R.drawable.register2)

        btnRegister.setOnClickListener {
            viewModel.register(
                email = etEmail.text.toString(),
                password = etPassword.text.toString(),
                name =  etName.text.toString()
            )
        }
    }

    private fun initObservable() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    if (it.loading) {
                        binding.btnRegister.visibility = View.GONE
                        binding.progressCircular.visibility = View.VISIBLE
                    } else {
                        binding.btnRegister.visibility = View.VISIBLE
                        binding.progressCircular.visibility = View.GONE
                    }

                    if (it.registerSuccess) {
                        findNavController().popBackStack()
                    }

                    if (it.error.isNotEmpty()) {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}