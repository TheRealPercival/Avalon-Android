package com.therealpercival.avalon.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ServerInfo(
    val version: String,
    val supabaseURL: String,
    val supabaseAnonKey: String
)
