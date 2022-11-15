package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException

class RegistrationNotFoundException : NotFoundException {

    constructor(id: Long) : super("Registration", id)
}