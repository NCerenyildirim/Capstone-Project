package com.example.quickup.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.quickup.Constants.COLLECTION_PATH
import com.example.quickup.Constants.E_MAIL
import com.example.quickup.Constants.FAILURE
import com.example.quickup.Constants.FORGOT
import com.example.quickup.Constants.SIGN_IN
import com.example.quickup.Constants.SIGN_UP
import com.example.quickup.Constants.SUCCESS
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class UsersRepository {

    var isSignIn = MutableLiveData<Boolean>()

    var isSignUp = MutableLiveData<Boolean>()
    var isForgot = MutableLiveData<Boolean>()




    private val auth = Firebase.auth
    private val db = Firebase.firestore

    fun signIn(eMail: String, password: String) {

        val addOnCompleteListener =
            auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    isSignIn.value = true
                    Log.d(SIGN_IN, SUCCESS)
                } else {
                    isSignIn.value = false
                    Log.w(SIGN_IN, FAILURE, task.exception)
                }
            }
    }

    fun signUp(eMail: String, password: String) {

        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val currentUser = auth.currentUser
                currentUser?.let { fbUsers ->
                    val user = hashMapOf(
                        E_MAIL to eMail

                    )

                    db.collection(COLLECTION_PATH).document(fbUsers.uid)
                        .set(user)
                        .addOnSuccessListener {
                            isSignUp.value = true
                            Log.d(SIGN_UP, SUCCESS)
                        }
                        .addOnFailureListener { e ->
                            isSignUp.value = false
                            Log.w(SIGN_UP, FAILURE, e)
                        }
                }

            } else {
                isSignUp.value = false
                Log.w(SIGN_UP, FAILURE, task.exception)
            }
        }
    }



    fun forgot() {
        auth.signOut()
    }
    fun forgot(eMail: String) {

        val addOnCompleteListener =
            auth.sendPasswordResetEmail(eMail).addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    isForgot.value = true
                    Log.d(FORGOT, SUCCESS)
                } else {
                    isForgot.value = false
                    Log.w(FORGOT, FAILURE, task.exception)
                }
            }
    }
}