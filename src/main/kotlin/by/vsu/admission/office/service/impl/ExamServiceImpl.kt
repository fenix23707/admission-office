package by.vsu.admission.office.service.impl

import by.vsu.admission.office.dto.ExamDto
import by.vsu.admission.office.dto.StudentDto
import by.vsu.admission.office.dto.assign.StudentExamAssignDto
import by.vsu.admission.office.exception.forbidden.UserAccessException
import by.vsu.admission.office.exception.notfound.ExamNotFoundException
import by.vsu.admission.office.model.Exam
import by.vsu.admission.office.model.Subject
import by.vsu.admission.office.repository.api.ExamRepository
import by.vsu.admission.office.service.api.ExamService
import by.vsu.admission.office.service.api.StudentService
import by.vsu.admission.office.service.api.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExamServiceImpl @Autowired constructor(
    private val examRepository: ExamRepository,
    private val studentService: StudentService,
    private val userService: UserService
) : ExamService {

    override fun getById(id: Long): Exam {
        return examRepository.findByIdOrNull(id) ?: throw ExamNotFoundException(id)
    }

    override fun createWithSubject(subject: Subject, examDto: ExamDto): ExamDto {
        val exam = examDto.copy(subject = subject)
        return ExamDto(examRepository.save(exam.toModel()))
    }

    override fun updateById(id: Long, examDto: ExamDto): ExamDto {
        val old = getById(id)
        return ExamDto(examRepository.save(examDto.toModel(old)))
    }

    override fun deleteById(id: Long) {
        checkIfExistById(id)
        examRepository.deleteById(id)
    }

    override fun assignStudent(examId: Long, studentId: Long): StudentExamAssignDto {
        val student = studentService.getById(studentId)
        val exam = getById(examId)
        exam.users.add(student.user)
        examRepository.save(exam)
        return StudentExamAssignDto(StudentDto(student), ExamDto(exam))
    }

    override fun getAllDto(pageable: Pageable): Page<ExamDto> {
        return examRepository.findAll(pageable).map { ExamDto(it) }
    }

    override fun getByIdDto(id: Long): ExamDto {
        return ExamDto(getById(id))
    }

    override fun getAllByUserIdDto(userId: Long): List<ExamDto> {
        return userService.getById(userId).exams.map { ExamDto(it) }
    }

    override fun checkHasAccessToExam(userId: Long, examId: Long) {
        val exam = getById(examId)
        if (!exam.users.map { it.id }.contains(userId)) {
            throw UserAccessException("User with id = $userId don't have access to exam with id = ${exam.id}.")
        }
    }

    private fun checkIfExistById(id: Long) {
        if (examRepository.countById(id) != 1L) {
            throw ExamNotFoundException(id)
        }
    }
}