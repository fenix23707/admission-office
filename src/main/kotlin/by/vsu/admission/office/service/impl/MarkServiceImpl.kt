package by.vsu.admission.office.service.impl

import by.vsu.admission.office.dto.MarkDto
import by.vsu.admission.office.exception.forbidden.MarkUniqueException
import by.vsu.admission.office.exception.notfound.MarkNotFoundException
import by.vsu.admission.office.model.Mark
import by.vsu.admission.office.repository.api.MarkRepository
import by.vsu.admission.office.service.api.ExamService
import by.vsu.admission.office.service.api.MarkService
import by.vsu.admission.office.service.api.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MarkServiceImpl @Autowired constructor(
    private val markRepository: MarkRepository,
    private val examService: ExamService,
    private val studentService: StudentService,
) : MarkService {

    override fun getById(id: Long): Mark {
        return markRepository.findByIdOrNull(id) ?: throw MarkNotFoundException(id)
    }

    override fun create(markDto: MarkDto, examId: Long, studentId: Long): MarkDto {
        val exam = examService.getById(examId)
        val student = studentService.getById(studentId)
        val mark = markDto.toModel(exam = exam, student = student)
        checkUnique(mark)

        return MarkDto(markRepository.save(mark))
    }


    override fun delete(id: Long) {
        markRepository.deleteById(id)
    }

    private fun checkUnique(mark: Mark) {
        val examId = mark.exam.id!!
        val studentId = mark.student.id!!
        if (markRepository.countByExamIdAndStudentId(examId, studentId) > 0) {
            throw MarkUniqueException(examId, studentId)
        }
    }

    override fun getAllDtoByStudentId(studentId: Long): List<MarkDto> {
        return markRepository.findAllByStudentId(studentId).map { MarkDto(it) }
    }
}