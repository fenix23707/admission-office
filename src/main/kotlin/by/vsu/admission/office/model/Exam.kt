package by.vsu.admission.office.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
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
    val users: MutableSet<User>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Exam

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , time = $time , duration = $duration , classroom = $classroom , subject = $subject )"
    }
}
