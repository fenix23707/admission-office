package by.vsu.admission.office.controller

import by.vsu.admission.office.dto.ExamDto
import by.vsu.admission.office.dto.assign.StudentExamAssignDto
import by.vsu.admission.office.dto.pageable.PageableExamDto
import by.vsu.admission.office.service.api.ExamService
import by.vsu.admission.office.service.api.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exams")
class ExamController @Autowired constructor(
    private val examService: ExamService,
    private val subjectService: SubjectService
) {

    @GetMapping
    @PreAuthorize("hasAuthority('EXAM_READ')")
    fun getAll(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "\${pagination.size}") size: Int
    ): PageableExamDto {
        val page = examService.getAllDto(PageRequest.of(page, size))
        return PageableExamDto(page)
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('EXAM_READ_BY_ID')")
    fun getById(@PathVariable id: Long): ExamDto {
        return examService.getByIdDto(id)
    }

    @PostMapping("/subjects/{subjectId}")
    @PreAuthorize("hasAuthority('EXAM_CREATE')")
    fun createBySubject(
        @PathVariable subjectId: Int,
        @RequestBody examDto: ExamDto
    ): ExamDto {
        val subject = subjectService.getById(subjectId)
        return examService.createWithSubject(subject, examDto)
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EXAM_UPDATE')")
    fun update(
        @PathVariable id: Long,
        @RequestBody examDto: ExamDto
    ): ExamDto {
        return examService.updateById(id, examDto)
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('EXAM_DELETE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        examService.deleteById(id)
    }

    @PostMapping("{examId}/assign/students/{studentId}")
    @PreAuthorize("hasAuthority('EXAM_ASSIGN')")
    fun assignStudent(
        @PathVariable examId: Long,
        @PathVariable studentId: Long
    ): StudentExamAssignDto {
        return examService.assignStudent(examId, studentId)
    }
}