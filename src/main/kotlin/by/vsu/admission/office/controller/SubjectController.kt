package by.vsu.admission.office.controller

import by.vsu.admission.office.dto.pageable.PageableSubjectDto
import by.vsu.admission.office.service.api.SubjectService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subjects")
class SubjectController @Autowired constructor(
    private val subjectService: SubjectService
){

    @GetMapping
    fun getAll(@RequestParam(defaultValue = "0") page: Int,
               @RequestParam(defaultValue = "3") size: Int) : PageableSubjectDto {
        val page = subjectService.getAll(PageRequest.of(page, size))
        return PageableSubjectDto(page)
    }
}