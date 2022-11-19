package by.vsu.admission.office.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "marks")
data class Mark(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val score: Int,

    @ManyToOne
    @JoinColumn(name = "exam_id")
    val exam: Exam,

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: Student,
)
