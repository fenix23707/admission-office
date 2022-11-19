package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException

class MarkNotFoundException : NotFoundException {

    constructor(id: Long) : super("Mark", id)
}