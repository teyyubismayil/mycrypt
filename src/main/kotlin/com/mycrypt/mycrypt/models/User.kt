package com.mycrypt.mycrypt.models

import com.mycrypt.mycrypt.dtos.RegistrationRequest
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime
import java.util.*

@Document
data class User(
    @Id
    val id: ObjectId = ObjectId.get(),
    val regDate: LocalDateTime = LocalDateTime.now(),
    @JvmField private val username: String,
    @JvmField private val password: String,
    val email: String
): UserDetails {

    constructor(
        registrationRequest: RegistrationRequest,
        passwordEncoder: PasswordEncoder
    ): this(
        username = registrationRequest.username,
        password = passwordEncoder.encode(registrationRequest.password),
        email = registrationRequest.email
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList()
    }

    override fun getPassword() = this.password

    override fun getUsername() = this.username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
