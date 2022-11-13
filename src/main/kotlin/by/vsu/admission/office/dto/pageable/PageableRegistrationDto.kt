package by.vsu.admission.office.dto.pageable

import by.vsu.admission.office.model.Registration
import org.springframework.data.domain.Page

class PageableRegistrationDto(
    page: Page<Registration>
) : PageableDto(page) {
    val registrations: List<Registration> = page.content
}
