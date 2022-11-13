package by.vsu.admission.office.service.impl

import by.vsu.admission.office.exception.notfound.StudentNotFoundException
import by.vsu.admission.office.model.Student
import by.vsu.admission.office.repository.api.StudentRepository
import by.vsu.admission.office.service.api.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl @Autowired constructor(
    private val studentRepository: StudentRepository
) : StudentService{

    override fun getById(id: Long): Student {
        return studentRepository.findByIdOrNull(id) ?: throw StudentNotFoundException(id)
    }

    override fun getByUserId(userId: Long): Student {
        return studentRepository.findByUserId(userId) ?: throw StudentNotFoundException("userId", userId)
    }
}