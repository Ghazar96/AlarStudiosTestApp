package com.example.alarstudiostestapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.alarstudiostestapp.di.appModule
import com.example.alarstudiostestapp.loginPage.LoginFragment
import com.example.alarstudiostestapp.mainPage.MainFragment
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainActivity : AppCompatActivity() {

    private val appViewModel: AppSharedViewModel by viewModel<AppSharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            // Koin Android logger
            androidLogger(Level.ERROR)
            //inject Android context
            androidContext(this@MainActivity)
            // use modules
            modules(appModule(getPreferences(Context.MODE_PRIVATE)))
        }
        appViewModel.openMainPageLiveData.observe(this, {
            openMainFragment()
        })

        appViewModel.openLoginPageLiveData.observe(this, {
            addLoginFragment()
        })
    }

    private fun openMainFragment() {
        openFragment(MainFragment(), true)
    }

    private fun addLoginFragment() {
        openFragment(LoginFragment(), false)
    }

    private fun openFragment(fragment: Fragment, isReplace: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(
            android.R.animator.fade_in,
            android.R.animator.fade_out
        )

        if (isReplace) {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment, LOGIN_FRAGMENT_TAG)
        } else {
            fragmentTransaction.add(R.id.fragmentContainer, fragment, LOGIN_FRAGMENT_TAG)
        }

        fragmentTransaction.addToBackStack(LOGIN_FRAGMENT_TAG)
        if (!supportFragmentManager.isStateSaved) {
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.commitAllowingStateLoss()
        }
    }

    companion object {
        const val LOGIN_FRAGMENT_TAG = "login_fragment"
    }
}
