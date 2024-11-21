package com.karla.cookyhome.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.karla.cookyhome.R
import com.karla.cookyhome.databinding.ActivityCreateAccountBinding
import com.karla.cookyhome.utils.base.BaseActivity
import com.karla.cookyhome.viewmodel.CreateAccountViewModel

class CreateAccountActivity : BaseActivity<ActivityCreateAccountBinding, CreateAccountViewModel>() {
    override fun createViewModel(): CreateAccountViewModel {
        return ViewModelProvider(this).get(CreateAccountViewModel:: class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityCreateAccountBinding {
        return ActivityCreateAccountBinding.inflate(layoutInflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding!!.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding!!.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding!!.btnCreateAccount.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    SignUpActivity::class.java
                )
            )
        }

        binding!!.btnLogin.setOnClickListener {
            startActivity(
                Intent(
                    this@CreateAccountActivity,
                    LoginActivity::class.java
                )
            )
        }

    }

}