package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException


class SubjectNotFoundException : NotFoundException {

    constructor(id: Int): super("Subject", id)
}