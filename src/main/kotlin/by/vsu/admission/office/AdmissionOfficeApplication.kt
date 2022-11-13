package by.vsu.admission.office

import by.vsu.admission.office.security.RsaKeyProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties::class)
class AdmissionOfficeApplication

fun main(args: Array<String>) {
	runApplication<AdmissionOfficeApplication>(*args)
}
