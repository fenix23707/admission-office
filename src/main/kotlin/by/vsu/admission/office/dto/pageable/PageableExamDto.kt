package by.vsu.admission.office.dto.pageable

import by.vsu.admission.office.dto.ExamDto
import org.springframework.data.domain.Page

class PageableExamDto(
    page: Page<ExamDto>
) : PageableDto(page) {
    val exams: List<ExamDto> = page.content
}