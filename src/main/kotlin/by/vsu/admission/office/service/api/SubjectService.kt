package by.vsu.admission.office.service.api

import by.vsu.admission.office.dto.SubjectDto
import by.vsu.admission.office.model.Subject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface SubjectService {
    fun getById(id: Int): Subject

    fun getAllDto(pageable: Pageable): Page<SubjectDto>
}