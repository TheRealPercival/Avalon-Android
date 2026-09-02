package com.therealpercival.avalon.domain.model

data class AuthSession(
    val accessToken: String,
    val refreshToken: String
)
