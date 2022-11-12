package by.vsu.admission.office.service.impl

import by.vsu.admission.office.dto.SubjectDto
import by.vsu.admission.office.model.Subject
import by.vsu.admission.office.repository.SubjectRepository
import by.vsu.admission.office.service.api.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class SubjectServiceImpl @Autowired constructor(
    private val subjectRepository: SubjectRepository
): SubjectService {

    override fun getAll(pageable: Pageable): Page<SubjectDto> {
        return subjectRepository.findAll(pageable).map { SubjectDto(it) }
    }
}