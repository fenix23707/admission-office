package by.vsu.admission.office.security

import by.vsu.admission.office.model.Role
import by.vsu.admission.office.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserSecurity(
    val id: Long,
    private val username: String,
    private val password: String,
    private val authorities: MutableCollection<GrantedAuthority>,
    private val isActive: Boolean = true,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = authorities
    override fun getPassword(): String = password
    override fun getUsername(): String = username
    override fun isAccountNonExpired(): Boolean = isActive
    override fun isAccountNonLocked(): Boolean = isActive
    override fun isCredentialsNonExpired(): Boolean = isActive
    override fun isEnabled(): Boolean = isActive

    companion object {
        fun fromUser(user: User): UserSecurity {
            return UserSecurity(
                id = user.id!!,
                username = user.username,
                password = user.password,
                authorities = user.role.toAuthorities(),
                isActive = true
            )
        }

        private fun Role.toAuthorities(): MutableCollection<GrantedAuthority> {
            return this.permissions
                .map { "${it.scope}_${it.action}" }
                .map { SimpleGrantedAuthority(it) }
                .toMutableList()
        }
    }
}
