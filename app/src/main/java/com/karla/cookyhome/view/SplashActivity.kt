package com.karla.cookyhome.view

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.karla.cookyhome.R
import com.karla.cookyhome.databinding.ActivitySplashBinding
import com.karla.cookyhome.utils.base.BaseActivity
import com.karla.cookyhome.view.home.HomeActivity
import com.karla.cookyhome.view.profile.CreateAccountActivity
import com.karla.cookyhome.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(){
    override fun createViewModel(): SplashViewModel {
        return ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.getIsLogin().observe(this, Observer { isLogin ->
            if (isLogin) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, CreateAccountActivity::class.java))
            }
            finish()
        })



        (findViewById<View>(R.id.splashLottie) as LottieAnimationView).addAnimatorListener(object :
            Animator.AnimatorListener {
            override fun onAnimationStart(animator: Animator) {

            }
            override fun onAnimationEnd(animator: Animator) {
                /*startActivity(Intent(this@SplashActivity, CreateAccountActivity::class.java))
                finish()*/
                viewModel.isLogin()
            }

            override fun onAnimationCancel(animator: Animator) {

            }
            override fun onAnimationRepeat(animator: Animator) {

            }
        })

    }
}