package by.vsu.admission.office.dto.pageable

import by.vsu.admission.office.dto.SubjectDto
import org.springframework.data.domain.Page

class PageableSubjectDto(
    page: Page<SubjectDto>
) : PageableDto(page) {
    val subjects: List<SubjectDto> = page.content
}
