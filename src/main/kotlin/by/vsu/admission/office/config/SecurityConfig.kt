package by.vsu.admission.office.config

import by.vsu.admission.office.security.JwtToUserConverter
import by.vsu.admission.office.security.RsaKeyProperties
import com.nimbusds.jose.jwk.JWK
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)

class SecurityConfig @Autowired constructor(
    private val userDetailsService: UserDetailsService,
    private val jwtToUserConverter: JwtToUserConverter,
    private val rsaKeys: RsaKeyProperties
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .authorizeRequests {
                it
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .oauth2ResourceServer {
                it.jwt().jwtAuthenticationConverter(jwtToUserConverter)
            }
            .csrf().disable()
            .httpBasic().disable()
        .build()
    }

    @Bean
    @Primary
    fun jwtDecoder(): JwtDecoder? {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.accessTokenPublicKey).build()
    }

    @Bean
    @Primary
    fun jwtEncoder(): JwtEncoder? {
        val jwk: JWK =
            RSAKey.Builder(rsaKeys.accessTokenPublicKey).privateKey(rsaKeys.accessTokenPrivateKey).build()
        val jwkSource: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwkSource)
    }

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider? {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailsService)
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}