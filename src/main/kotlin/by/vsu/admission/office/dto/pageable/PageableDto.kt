package by.vsu.admission.office.dto.pageable

import org.springframework.data.domain.Page

abstract class PageableDto(page: Page<*>) {

    val currentPage: Int = page.number
    val totalItems: Long = page.totalElements
    val totalPages: Int = page.totalPages
}