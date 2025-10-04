package com.arsenal.sync.core.domain.model

data class User(
    val firstName: String = "",
    val email: String = "",
    val isVerified : Boolean = false
)