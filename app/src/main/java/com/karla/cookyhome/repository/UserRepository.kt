package com.karla.cookyhome.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.karla.cookyhome.utils.firebase.CustomOnCompleteListener

class UserRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun isLogin(): Boolean {
        return firebaseAuth.currentUser != null
    }

    fun getUserId(): String? {
        return if (firebaseAuth.currentUser != null) firebaseAuth.currentUser!!.uid else ""
    }

    fun login(
        onCompleteListener: CustomOnCompleteListener<AuthResult?>,
        email: String?,
        password: String?
    ) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(
                onCompleteListener
            )
    }

    fun singUp(
        onCompleteListener: CustomOnCompleteListener<AuthResult?>,
        email: String?,
        password: String?
    ) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(
                onCompleteListener
            )
    }

    fun recoverPassword(email: String?, onCompleteListener: CustomOnCompleteListener<Void?>) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email!!).addOnCompleteListener(
            onCompleteListener
        )
    }


}