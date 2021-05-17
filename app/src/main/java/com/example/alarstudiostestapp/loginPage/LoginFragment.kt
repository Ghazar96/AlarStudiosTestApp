package com.example.alarstudiostestapp.loginPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.alarstudiostestapp.AppSharedViewModel
import com.example.alarstudiostestapp.R
import kotlinx.android.synthetic.main.fragment_loing.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by stateViewModel(
        bundle = {
            Bundle()
        }
    )

    private val viewModel by sharedViewModel<AppSharedViewModel>()//lazy { requireActivity().getviewmodel<AppSharedViewModel>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        loginButton.setOnClickListener {
            loginButtonClicked()
        }
    }

    private fun initObservers() {
        loginViewModel.showErrorLiveData.observe(
            viewLifecycleOwner,
            { errorText ->
                handleError(errorText)
            }
        )

        loginViewModel.loginCompleteLiveData.observe(
            viewLifecycleOwner,
            {
                handleLoginComplete()
            }
        )
    }

    private fun loginButtonClicked() {
        showLoading()
        disableLoginButton()
        hideErrorText()
        loginViewModel.login(
            username = username.text.toString(),
            password = password.text.toString()
        )
    }

    private fun handleLoginComplete() {
        hideLoading()
        viewModel.openMainPage("")
    }

    private fun handleError(errorText: String) {
        hideLoading()
        enableLoginButton()
        showErrorText(errorText)
    }

    private fun hideErrorText() {
        errorText.visibility = View.GONE
    }

    private fun showErrorText(text: String) {
        errorText.visibility = View.VISIBLE
        errorText.text = text
    }

    private fun disableLoginButton() {
        loginButton.isEnabled = false
    }

    private fun enableLoginButton() {
        loginButton.isEnabled = true
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }
}
