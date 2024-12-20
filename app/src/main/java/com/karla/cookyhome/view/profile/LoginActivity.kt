package com.karla.cookyhome.view.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.karla.cookyhome.R
import com.karla.cookyhome.utils.constants.Constants
import com.karla.cookyhome.databinding.ActivityLoginBinding
import com.karla.cookyhome.utils.StringUtils
import com.karla.cookyhome.utils.base.BaseActivity
import com.karla.cookyhome.view.home.HomeActivity
import com.karla.cookyhome.viewmodel.LoginViewModel
import com.karla.learningverbs.utils.Tools

class LoginActivity:  BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun createViewBinding(layoutInflater: LayoutInflater?): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater!!)
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

        binding!!.btnForgotPassword.setOnClickListener {
            val intent = Intent(this@LoginActivity, GetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding!!.btnLogin.setOnClickListener {
            if (StringUtils.validateEmail(binding!!.editTextEmail.text.toString())) {
                binding!!.editTextEmail.error = null
                if (StringUtils.validatePassword(binding!!.editTextPassword.text.toString())) {
                    binding!!.editTextPassword.error = null
                    viewModel!!.doLogin(
                        binding!!.editTextEmail.text.toString(),
                        binding!!.editTextPassword.text.toString()
                    )
                } else {
                    binding!!.editTextPassword.error = getString(R.string.msg_add_password)
                }
            } else {
                binding!!.editTextEmail.error = getString(R.string.msg_email_not_valid)
            }
        }
        createUserWithEmail()
        configSwitchSaveUser()
        saveData

    }

    private fun createUserWithEmail() {
        viewModel?.getFireBaseUser()?.observe(this@LoginActivity, Observer { aBoolean ->
            if (aBoolean != null) {
                startActivity(
                    Intent(this@LoginActivity, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        })
    }

    private val saveData: Unit
        private get() {
            val userName = Tools.getStringPreference(Constants.USERNAME_SAVE)
            if (!userName!!.isEmpty()) {
                binding!!.editTextEmail.setText(userName)
                binding!!.checkBoxRememberUser.isChecked = true
            }
        }

    private fun configSwitchSaveUser() {
        binding!!.checkBoxRememberUser.setOnCheckedChangeListener { compoundButton, b ->
            val userName = binding!!.editTextEmail.text.toString()
            if (!userName.isEmpty()) {
                if (compoundButton.isChecked) {
                    Tools.setStringPreference(Constants.USERNAME_SAVE, userName)
                } else {
                    Tools.setStringPreference(Constants.USERNAME_SAVE, "")
                }
            } else {
                binding!!.checkBoxRememberUser.isChecked = false
                Tools.showSnackMessage(
                    binding!!.CoordinatorLayoutContainerLogin,
                    getString(R.string.lbl_email)
                )
            }
        }
    }


        /*
        binding!!.btnLogin.setOnClickListener {
            if (StringUtils.validateEmail(binding!!.editTextEmail.text.toString())) {
                binding!!.editTextEmail.error = null
                if (StringUtils.validatePassword(binding!!.editTextPassword.text.toString())) {
                    binding!!.editTextPassword.error = null
                    viewModel!!.doLogin(
                        binding!!.editTextEmail.text.toString(),
                        binding!!.editTextPassword.text.toString()
                    )
                } else {
                    binding!!.editTextPassword.error = getString(R.string.msg_add_password)
                }
            } else {
                binding!!.editTextEmail.error = getString(R.string.msg_email_not_valid)
            }
        }
        createUserWithEmail()
        configSwitchSaveUser()
        saveData
    }

    private fun createUserWithEmail() {
        viewModel?.getFireBaseUser()?.observe(this@LoginActivity, Observer { aBoolean ->
            if (aBoolean != null) {
                startActivity(
                    Intent(this@LoginActivity, HomeActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        })
    }

    private val saveData: Unit
        private get() {
            val userName = Tools.getStringPreference(Constants.USERNAME_SAVE)
            if (!userName!!.isEmpty()) {
                binding!!.editTextEmail.setText(userName)
                binding!!.checkBoxRememberUser.isChecked = true
            }
        }

    private fun configSwitchSaveUser() {
        binding!!.checkBoxRememberUser.setOnCheckedChangeListener { compoundButton, b ->
            val userName = binding!!.editTextEmail.text.toString()
            if (!userName.isEmpty()) {
                if (compoundButton.isChecked) {
                    Tools.setStringPreference(Constants.USERNAME_SAVE, userName)
                } else {
                    Tools.setStringPreference(Constants.USERNAME_SAVE, "")
                }
            } else {
                binding!!.checkBoxRememberUser.isChecked = false
                Tools.showSnackMessage(
                    binding!!.CoordinatorLayoutContainerLogin,
                    getString(R.string.lbl_email)
                )
            }
        }*/
}