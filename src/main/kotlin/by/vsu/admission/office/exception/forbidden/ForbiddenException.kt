package by.vsu.admission.office.exception.forbidden

import by.vsu.admission.office.exception.ServiceException
import org.springframework.http.HttpStatus

abstract class ForbiddenException(message: String) : ServiceException(HttpStatus.FORBIDDEN, message) {
}