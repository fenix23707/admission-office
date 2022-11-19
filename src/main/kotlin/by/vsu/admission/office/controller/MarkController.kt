package by.vsu.admission.office.controller

import by.vsu.admission.office.dto.MarkDto
import by.vsu.admission.office.security.UserSecurity
import by.vsu.admission.office.service.api.ExamService
import by.vsu.admission.office.service.api.MarkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/marks")
class MarkController @Autowired constructor(
    private val markService: MarkService,
    private val examService: ExamService,
) {

    @PostMapping("/exams/{examId}/students/{studentId}")
    @PreAuthorize("hasAuthority('MARK_CREATE')")
    fun create(
        @PathVariable examId: Long,
        @PathVariable studentId: Long,
        @RequestBody markDto: MarkDto,
        authentication: Authentication
    ): MarkDto {
        val userId = (authentication.principal as UserSecurity).id
        examService.checkHasAccessToExam(userId, examId)
        return markService.create(markDto, examId, studentId)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable id: Long,
        authentication: Authentication
    ) {
        val userId = (authentication.principal as UserSecurity).id
        val examId = markService.getById(id).exam.id!!
        examService.checkHasAccessToExam(userId, examId)
        markService.delete(id)
    }
}