package by.vsu.admission.office.security

import org.springframework.boot.context.properties.ConfigurationProperties
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix = "jwt")
data class RsaKeyProperties(
    var accessTokenPrivateKey: RSAPrivateKey? = null,
    var accessTokenPublicKey: RSAPublicKey? = null
)