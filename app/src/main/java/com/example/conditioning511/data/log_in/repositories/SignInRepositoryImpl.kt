package com.example.conditioning511.data.log_in.repositories

import androidx.activity.compose.registerForActivityResult
import androidx.compose.runtime.Composable
import com.example.conditioning511.data.log_in.contracts.GoogleContract
import com.example.conditioning511.domain.log_in.repositories.SignInRepository

class SignInRepositoryImpl(
    private val googleContract: GoogleContract
) : SignInRepository {

    @Composable
    override fun signIn() {
        registerForActivityResult(googleContract) { id ->
            println(id)
        }
    }

    override fun signOut() {
        googleContract.signOut()
    }
}