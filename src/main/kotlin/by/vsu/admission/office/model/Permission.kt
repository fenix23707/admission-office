package by.vsu.admission.office.model

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "permissions")
data class Permission(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    val scope: PermissionScope,

    @Enumerated(EnumType.STRING)
    val action: PermissionAction,

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    val roles: Set<Role> = emptySet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Permission

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , scope = $scope , action = $action )"
    }
}
