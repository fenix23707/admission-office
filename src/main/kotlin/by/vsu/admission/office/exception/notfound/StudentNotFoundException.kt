package by.vsu.admission.office.exception.notfound

import com.epam.carapi.exception.notfound.NotFoundException


class StudentNotFoundException : NotFoundException {

    constructor(id: Long) : super("Student", id)
    constructor(fieldName: String, value: Any) : super("Student with $fieldName = $value not found.")
}