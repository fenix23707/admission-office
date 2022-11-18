package by.vsu.admission.office.model

import by.vsu.admission.office.dto.ExamDto
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,

    val password: String,

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role,

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    @JsonIgnore
    val exams: Set<Exam>
)
