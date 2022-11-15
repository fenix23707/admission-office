package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException

class ExamNotFoundException : NotFoundException {

    constructor(id: Long) : super("Exam", id)
}