package by.vsu.admission.office.security

import by.vsu.admission.office.dto.JwtAuthenticationResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit

@Component
class TokenService @Autowired constructor(
    private val jwtEncoder: JwtEncoder
) {

    fun createToken(authentication: Authentication): JwtAuthenticationResponse {
        if (authentication.principal !is UserSecurity) {
            throw BadCredentialsException("Principal ${authentication.principal.javaClass} is not of UserSecurity type")
        }
        return JwtAuthenticationResponse(
            (authentication.principal as UserSecurity).id,
            generateToken(authentication)
        )
    }

    private fun generateToken(authentication: Authentication): String {
        val userSecurity = authentication.principal as UserSecurity
        val now = Instant.now()
        val claimsSet = JwtClaimsSet.builder()
            .issuer("admission-office")
            .issuedAt(now)
            .expiresAt(now.plus(10, ChronoUnit.MINUTES))
            .subject(userSecurity.username)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).tokenValue
    }
}