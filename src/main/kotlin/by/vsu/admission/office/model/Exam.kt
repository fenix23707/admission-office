package by.vsu.admission.office.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "exams")
data class Exam(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val time: LocalDateTime,

    val duration: Duration,

    val classroom: String,

    @ManyToOne
    @JoinColumn(name = "subject_id")
    val subject: Subject,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_exams",
        joinColumns = [JoinColumn(name = "exam_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    @JsonIgnore
    val users: List<User>
)
