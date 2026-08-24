package com.therealpercival.avalon.domain.model

data class User(
    val displayName: String,
    val accountName: String,
    val isAdmin: Boolean = false
)
