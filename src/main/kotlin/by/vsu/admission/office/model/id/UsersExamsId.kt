package by.vsu.admission.office.model.id

import by.vsu.admission.office.model.Exam
import org.apache.catalina.User
import javax.persistence.Embeddable

@Embeddable
data class UsersExamsId(

    val user: User,
    val exam: Exam,
)
