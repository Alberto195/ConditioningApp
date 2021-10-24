package com.example.conditioning511.data.log_in.contracts

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleContract : ActivityResultContract<Unit, String?>() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private val gso: GoogleSignInOptions = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(defaultWebClientId)
        .requestEmail()
        .requestServerAuthCode(defaultWebClientId)
        .build()

    fun signOut() {
        if (this::googleSignInClient.isInitialized) {
            googleSignInClient.signOut()
        }
    }

    override fun createIntent(context: Context, input: Unit): Intent {
        googleSignInClient = GoogleSignIn.getClient(context, gso)

        return googleSignInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return activityResult(intent)
    }

    private fun activityResult(data: Intent?): String? {
        val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
        val exception = task.exception
        return if (task.isSuccessful) {
            try {
                // Google Sign In was successful
                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                Log.d("Sign In Fragment", "firebaseAuthWithGoogle:" + account?.id)
                account?.idToken
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Sign In Fragment", "Google sign in failed", e)
                null
            }
        } else {
            Log.w("Sign In Fragment", exception.toString())
            null
        }
    }


    companion object {
        const val defaultWebClientId =
            "453973634644-8e4ql3dpshnu53jsqtsshtfcpo189d7h.apps.googleusercontent.com"
        const val defaultAndroidClientId =
            "453973634644-9c4nbetntm759cjdmvg3eob2evhqdklj.apps.googleusercontent.com"
    }
}