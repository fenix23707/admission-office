package by.vsu.admission.office.advice

import by.vsu.admission.office.dto.ApiErrorMessage
import by.vsu.admission.office.exception.ServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(ServiceException::class)
    fun serviceExceptionHandler(ex: ServiceException): ResponseEntity<ApiErrorMessage> {
        val apiErrorMessage = ApiErrorMessage(
            status = ex.status.name,
            message = ex.message,
        )
        return ResponseEntity(apiErrorMessage, ex.status)
    }

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptionHandler(ex: AuthenticationException): ResponseEntity<ApiErrorMessage> {
        val status = HttpStatus.UNAUTHORIZED
        val apiErrorMessage = ApiErrorMessage(
            status = status.name,
            message = "authentication exception",
            debugMessage = ex.message
        )
        return ResponseEntity(apiErrorMessage, status)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun accessDeniedExceptionHandler(ex: AccessDeniedException): ResponseEntity<ApiErrorMessage> {
        val status = HttpStatus.FORBIDDEN
        val apiErrorMessage = ApiErrorMessage(
            status = status.name,
            message = "access denied",
            debugMessage = ex.message
        )
        return ResponseEntity(apiErrorMessage, status)
    }

    @ExceptionHandler(Exception::class)
    fun globalExceptionHandler(exception: Exception): ResponseEntity<ApiErrorMessage> {
        val apiErrorMessage = ApiErrorMessage(
            status = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = "Something went wrong",
            debugMessage = exception.message,
            stackTrace = exception.stackTraceToString()
        )
        return ResponseEntity(apiErrorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}