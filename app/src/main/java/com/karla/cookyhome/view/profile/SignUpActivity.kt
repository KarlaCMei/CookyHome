package com.karla.cookyhome.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.cookyhome.R
import com.karla.cookyhome.databinding.ActivitySignUpBinding
import com.karla.cookyhome.utils.StringUtils.validateEmail
import com.karla.cookyhome.utils.StringUtils.validatePassword
import com.karla.cookyhome.utils.StringUtils.validateSamePassword
import com.karla.cookyhome.utils.base.BaseActivity
import com.karla.cookyhome.view.home.HomeActivity
import com.karla.cookyhome.viewmodel.SignUpVewModel

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpVewModel>() {
    override fun createViewModel(): SignUpVewModel {
        return ViewModelProvider(this).get(SignUpVewModel:: class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater!!)
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


        binding.btnSignUp.setOnClickListener(View.OnClickListener {
            if (validateEmail(binding.editTextEmail.text.toString())) {
                binding.editTextEmail.error = null

                if (validatePassword(binding.editTextPassword.text.toString())) {
                    binding.editTextPassword.error = null

                    if (validateSamePassword(
                            binding.editTextPassword.text.toString(),
                            binding.editTextVerifyPassword.text.toString()
                        )
                    ) {
                        binding.editTextVerifyPassword.error = getString(R.string.msg_verify_password)
                    } else {
                        viewModel.singUp(
                            binding.editTextEmail.text.toString(),
                            binding.editTextPassword.text.toString()
                        )
                    }
                } else {
                    binding.editTextPassword.error = getString(R.string.msg_add_password)
                }

            } else {
                binding.editTextEmail.error = getString(R.string.msg_email_not_valid)
            }
        })

        viewModel.getfirebaseUser().observe(this, Observer { aBoolean ->
            if (aBoolean!!) {
                startActivity(
                    Intent(this@SignUpActivity, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        })
    }
}