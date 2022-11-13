package by.vsu.admission.office.controller

import by.vsu.admission.office.dto.JwtAuthenticationResponse
import by.vsu.admission.office.dto.LoginRequest
import by.vsu.admission.office.security.TokenService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController @Autowired constructor(
    private val daoAuthenticationProvider: DaoAuthenticationProvider,
    private val tokenService: TokenService,
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): JwtAuthenticationResponse {
        val authentication = daoAuthenticationProvider.authenticate(
            UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username, loginRequest.password)
        )
        return tokenService.createToken(authentication)
    }
}