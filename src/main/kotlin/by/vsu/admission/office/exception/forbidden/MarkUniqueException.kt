package by.vsu.admission.office.exception.forbidden

class MarkUniqueException : ForbiddenException {

    constructor(examId: Long, studentId: Long): super("Mark with examId = $examId and studentId = $studentId already exists.")
}