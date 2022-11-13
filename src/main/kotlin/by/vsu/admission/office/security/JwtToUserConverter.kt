package by.vsu.admission.office.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class JwtToUserConverter @Autowired constructor(
    private val userDetailsService: UserDetailsService
) : Converter<Jwt, UsernamePasswordAuthenticationToken>{

    override fun convert(source: Jwt): UsernamePasswordAuthenticationToken? {
        val userSecurity = userDetailsService.loadUserByUsername(source.subject)
        return UsernamePasswordAuthenticationToken(userSecurity, source, userSecurity.authorities)
    }
}