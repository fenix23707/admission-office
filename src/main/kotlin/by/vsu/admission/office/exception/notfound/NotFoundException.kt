package com.epam.carapi.exception.notfound

import by.vsu.admission.office.exception.ServiceException
import org.springframework.http.HttpStatus


abstract class NotFoundException(message: String) : ServiceException(HttpStatus.NOT_FOUND, message) {
    constructor(modelName: String, id: Long) : this("$modelName with id = $id not found.")
}
